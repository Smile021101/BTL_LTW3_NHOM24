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

import Q_A_Online.model.User;
import Q_A_Online.repository.AnswerRepository;
import Q_A_Online.repository.CommentARepository;
import Q_A_Online.repository.CommentQRepository;
import Q_A_Online.repository.QuestionRepository;
import Q_A_Online.repository.UserRepository;

@Controller
@RequestMapping("/aduser")

public class AdUserController {
	private final UserRepository userRepo;
	private final QuestionRepository questionRepo;
	private final AnswerRepository answerRepo;
	private final CommentARepository commentARepo;
	private final CommentQRepository commentQRepo;

	@Autowired
	public AdUserController(UserRepository userRepo, QuestionRepository questionRepo, AnswerRepository answerRepo, CommentARepository commentARepo,  CommentQRepository commentQRepo) {
		this.userRepo = userRepo;
		this.answerRepo = answerRepo;
		this.questionRepo = questionRepo;
		this.commentARepo = commentARepo;
		this.commentQRepo = commentQRepo;
	}

	@GetMapping("/")
	public String showManageUserHome(Model model, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute(user);
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		List<User> userrs = new ArrayList<>();
		for (User userr : users) {
			if (!userr.getUsername().equals(user.getUsername())) {
				userrs.add(userr);
			}
		}
		model.addAttribute("users", userrs);
		return "aduser";
	}
	
	@GetMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		commentARepo.deleteByUser_id(id);
		commentQRepo.deleteByUser_id(id);
		answerRepo.deleteByUser_id(id);
		questionRepo.deleteByUser_id(id);
		userRepo.deleteById(id);
		System.out.println(1);
		return "redirect:/aduser/";
	}
}
