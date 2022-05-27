package Q_A_Online.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Q_A_Online.model.Answer;
import Q_A_Online.model.Question;
import Q_A_Online.model.User;
import Q_A_Online.repository.AnswerRepository;
import Q_A_Online.repository.QuestionRepository;
import Q_A_Online.repository.UserRepository;

@Controller
@RequestMapping("/user")

public class UserController {
	private final UserRepository userRepo;
	private final QuestionRepository questionRepo;
	private final AnswerRepository answerRepo;
	private long id;

	@Autowired
	public UserController(UserRepository userRepo, QuestionRepository questionRepo, AnswerRepository answerRepo) {
		this.userRepo = userRepo;
		this.answerRepo = answerRepo;
		this.questionRepo = questionRepo;
		this.id = 0;
	}

	@GetMapping("/")
	public String showUserProfileHome(Model model, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");

		if (user == null) {
			return "redirect:/login";
		}
		this.id = user.getId();
		List<Question> questions = new ArrayList<>();
		questionRepo.findByUser_id(user.getId()).forEach(questions::add);
		List<Answer> answers = new ArrayList<>();
		answerRepo.findByUser_id(user.getId()).forEach(answers::add);
		user.setQuestions(questions);
		user.setAnswer(answers);
		model.addAttribute("user", user);
		return "user";
	}

	@PostMapping
	public String editUser(User user, HttpSession session) {
		Optional<User> user1 = userRepo.findById(this.id);
		String username = user1.get().getUsername();
		String password = user1.get().getPassword();
		user1.get().setUsername(user.getUsername());
		user1.get().setPassword(user.getPassword());
		user1.get().setEmail(user.getEmail());
		user1.get().setFullname(user.getFullname());
		user1.get().setPhone(user.getPhone());
		userRepo.save(user1.get());
		session.removeAttribute("currentUser");
		session.setAttribute("currentUser", user1.get());
		if(username.equals(user.getUsername())&&password.equals(user.getPassword())) {
			return "redirect:/ushome";
		}
		return "redirect:/login";
	}
}
