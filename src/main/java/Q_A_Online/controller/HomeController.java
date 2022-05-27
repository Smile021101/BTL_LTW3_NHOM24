package Q_A_Online.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Q_A_Online.model.Question;
import Q_A_Online.model.User;
import Q_A_Online.repository.QuestionRepository;
import Q_A_Online.repository.UserRepository;

@Controller
public class HomeController {
	private final QuestionRepository questionRepo;
	private final UserRepository userRepo;

	@Autowired
	public HomeController(QuestionRepository questionRepo, UserRepository userRepo) {
		this.questionRepo = questionRepo;
		this.userRepo = userRepo;
	}

	@GetMapping("/")
	public String showHomeForm(Model model, HttpSession sestion) {
		User user = (User) sestion.getAttribute("currentUser");
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute(user);
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		System.out.println(users.size());
		model.addAttribute("users", users);
		List<Question> questions = new ArrayList<>();
		questionRepo.findAll().forEach(questions::add);
		model.addAttribute("questions", questions);
		return "home";
	}

	

	@GetMapping("/Doisong")
	public String showQuestionOfMathForm(Model model, HttpSession sestion) {
		User user = (User) sestion.getAttribute("currentUser");
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute(user);
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		System.out.println(users.size());
		model.addAttribute("users", users);
		List<Question> questions = new ArrayList<>();
		questionRepo.findByField("Doi song").forEach(questions::add);
		model.addAttribute("questions", questions);
		return "home";
	}

	@GetMapping("/Nauan")
	public String showQuestionOfPhysicsForm(Model model, HttpSession sestion) {
		User user = (User) sestion.getAttribute("currentUser");
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute(user);
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		System.out.println(users.size());
		model.addAttribute("users", users);
		List<Question> questions = new ArrayList<>();
		questionRepo.findByField("Nau an").forEach(questions::add);
		model.addAttribute("questions", questions);
		return "home";
	}

	@GetMapping("/Hoctap")
	public String showQuestionOfChemistryForm(Model model, HttpSession sestion) {
		User user = (User) sestion.getAttribute("currentUser");
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute(user);
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		System.out.println(users.size());
		model.addAttribute("users", users);
		List<Question> questions = new ArrayList<>();
		questionRepo.findByField("Hoc tap").forEach(questions::add);
		if(questions.isEmpty()) {
			model.addAttribute("message", "Không có câu hỏi về lĩnh vực học tập");
			return "home";
		}
		model.addAttribute("questions", questions);
		return "home";
	}

	@GetMapping("search")
	public String search(Model model, @RequestParam(name = "body", required = false) String body, HttpSession sestion) {
		User user = (User) sestion.getAttribute("currentUser");
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute(user);
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		System.out.println(users.size());
		model.addAttribute("users", users);
		List<Question> list = null;
		if (body != null) {
			list = questionRepo.findByBodyContaining(body);
		} else {
			list = (List<Question>) questionRepo.findAll();
		}
		model.addAttribute("questions", list);
		return "home";
	}
}
