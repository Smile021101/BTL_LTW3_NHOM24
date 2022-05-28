package Q_A_Online.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Q_A_Online.model.Answer;
import Q_A_Online.model.Question;
import Q_A_Online.model.User;
import Q_A_Online.repository.AnswerRepository;
import Q_A_Online.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/answer")
public class AnswerController {
	private final AnswerRepository answerRepo;
	private final QuestionRepository questionRepo;
	private Question question;

	@Autowired
	public AnswerController(AnswerRepository answerRepo, QuestionRepository questionRepo) {
		this.answerRepo = answerRepo;
		this.questionRepo = questionRepo;
		this.question=new Question();
	}

	@GetMapping("/{id}")
	public String showAddAnswerForm(@PathVariable Long id, Model model, HttpSession sesstion) {
		  User user=(User) sesstion.getAttribute("currentUser");
		  model.addAttribute(user); 
		  Optional<Question> question=questionRepo.findById(id); 
		  Question question1 = question.get();
		  this.question=question1;
		  model.addAttribute("answer",new Answer());
		  model.addAttribute("question",question1);
		return "answer";
	}

	@PostMapping
	public String addAnswer(Answer answer, Model model, HttpSession sesstion) {
		answer.setQuestion(question);
		answer.setUser((User)sesstion.getAttribute("currentUser"));
		answerRepo.save(answer);
		log.info("Save Success");
		return "redirect:/question/"+question.getId();
	}
}
