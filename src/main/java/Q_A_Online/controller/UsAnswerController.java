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
import Q_A_Online.model.User;
import Q_A_Online.repository.AnswerRepository;
import Q_A_Online.repository.CommentARepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/usanswer")
public class UsAnswerController {
	private final AnswerRepository answerRepo;
	private final CommentARepository commentARepo;

	@Autowired
	public UsAnswerController(AnswerRepository answerRepo, CommentARepository commentARepo) {
		this.answerRepo=answerRepo;
		this.commentARepo=commentARepo;
	}

	@GetMapping("/")
	public String showAdQuestionForm(Model model,HttpSession session) {
		User user=(User) session.getAttribute("currentUser");
		model.addAttribute(user);
		List<Answer> answers=new ArrayList<>();
		answerRepo.findByUser_id(user.getId()).forEach(answers::add);
		model.addAttribute("answers",answers);
		return "usanswer";
	}

	@GetMapping("/{id}")
	public String deleteAnswer(@PathVariable Long id) {
		commentARepo.deleteByAnswer_id(id);
		answerRepo.deleteById(id);
		log.info("Delete Success");
		return "redirect:/usanswer/";
	}
}