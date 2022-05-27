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
import Q_A_Online.model.CommentA;
import Q_A_Online.model.User;
import Q_A_Online.repository.AnswerRepository;
import Q_A_Online.repository.CommentARepository;

@Controller
@RequestMapping("/comment")
public class CommentController {
	private final CommentARepository commentRepo;
	private final AnswerRepository answerRepo;
	private Answer answer;
	@Autowired
	public CommentController(CommentARepository commentRepo, AnswerRepository answerRepo) {
		this.commentRepo=commentRepo;
		this.answerRepo=answerRepo;
		this.answer=new Answer();
	}

	@GetMapping("/{id}")
	public String showAddForm(@PathVariable Long id,Model model,HttpSession session) {
		User user=(User) session.getAttribute("currentUser");
		model.addAttribute("user",user);
		Optional<Answer> answer1=answerRepo.findById(id);
		this.answer=answer1.get();
		model.addAttribute("answer",answer);
		model.addAttribute("commentA",new CommentA());
		return "comment";
	}

	@PostMapping
	public String addComment(CommentA commentA, Model model, HttpSession session) {
		commentA.setAnswer(answer);
		User user=(User) session.getAttribute("currentUser");
		commentA.setUser(user);
		System.out.println(answer.getQuestion().getBody());
		commentRepo.save(commentA);
		answerRepo.save(answer);
		return "redirect:/question/"+answer.getQuestion().getId();
	}
}
