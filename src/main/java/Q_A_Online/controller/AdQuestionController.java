package Q_A_Online.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Q_A_Online.model.Answer;
import Q_A_Online.model.Question;
import Q_A_Online.model.User;
import Q_A_Online.repository.AnswerRepository;
import Q_A_Online.repository.CommentARepository;
import Q_A_Online.repository.CommentQRepository;
import Q_A_Online.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/adquestion")
public class AdQuestionController {
	private final QuestionRepository questionRepo;
	private final AnswerRepository answerRepo;
	private final CommentARepository commentARepo;
	private final CommentQRepository commentQRepo;

	@Autowired
	public AdQuestionController(QuestionRepository questionRepo,CommentQRepository commentQRepo, AnswerRepository answerRepo, CommentARepository commentARepo) {
		this.questionRepo = questionRepo;
		this.commentQRepo=commentQRepo;
		this.answerRepo=answerRepo;
		this.commentARepo=commentARepo;
	}

	@GetMapping("/")
	public String showAdQuestionForm(Model model,HttpSession session) {
		User user=(User) session.getAttribute("currentUser");
		model.addAttribute(user);
		List<Question> questions=new ArrayList<>();
		questionRepo.findAll().forEach(questions::add);
		model.addAttribute("questions",questions);
		return "adquestion";
	}

	@GetMapping("/{id}")
	public String deleteQuestion(@PathVariable Long id) {
		commentQRepo.deleteByQuestion_id(id);
		List<Answer> answers=new ArrayList<>();
		answerRepo.findByQuestion_id(id).forEach(answers::add);
		for(Answer ans:answers) commentARepo.deleteByAnswer_id(ans.getId());
		answerRepo.deleteByQuestion_id(id);
		questionRepo.deleteById(id);
		log.info("Delete Success");
		return "redirect:/adquestion/";
	}
}
