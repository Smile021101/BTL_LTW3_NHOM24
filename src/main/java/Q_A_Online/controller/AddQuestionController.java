package Q_A_Online.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Q_A_Online.model.Question;
import Q_A_Online.model.Questiont;
import Q_A_Online.model.User;
import Q_A_Online.repository.QuestionRepository;
import Q_A_Online.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/addquestion")
public class AddQuestionController {
	private final QuestionRepository questionRepo;
	private final UserRepository userRepo;
	private Long id;

	@Autowired
	public AddQuestionController(QuestionRepository questionRepo,UserRepository userRepo) {
		this.questionRepo = questionRepo;
		this.userRepo=userRepo;
		this.id=(long) 0;
	}

	@GetMapping("/{id}")
	public String showAddForm(@PathVariable Long id,Model model,HttpSession session) {
		this.id=id;
		User user=(User) session.getAttribute("currentUser");
		model.addAttribute(user);
		model.addAttribute("question", new Questiont());
		List<String> List_field= new ArrayList<String>();
		List_field.add("Doi song");
		List_field.add("Nau an");
		List_field.add("Hoc tap");
		model.addAttribute("List_field",List_field);
		return "addquestion";
	}

	@PostMapping
	public String addQuestion(Questiont questiont, Model model) {
		Optional<User> user=userRepo.findById(id);
		if(user.isPresent()) {
			User user1=user.get();
			Question question=new Question();
			question.setBody(questiont.getBody());
			question.setField(questiont.getField());
			question.setUser(user1);
			questionRepo.save(question);
		}
		log.info("Save Success");
		return "redirect:/";
	}
}

