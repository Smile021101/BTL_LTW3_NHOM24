package Q_A_Online.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Q_A_Online.model.User;
import Q_A_Online.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
	private final UserRepository userRepo;

	@Autowired
	public LoginController(UserRepository userRepo) {

		this.userRepo = userRepo;

	}

	public User login(String username, String password) {
		User userIsExisted = userRepo.findByUsernameAndPassword(username, password);
		return userIsExisted;
	}

	@GetMapping
	public String showLoginForm(Model model, HttpSession session) {
		//session.removeAttribute("currentUser");
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping
	public String checkLogin(@Valid User user1, BindingResult result, Model model, HttpSession session) {
		if (userRepo.findByUsernameAndPassword(user1.getUsername(), user1.getPassword()) != null) {
			User user = (User) userRepo.findByUsernameAndPassword(user1.getUsername(), user1.getPassword());
			session.setAttribute("currentUser", user);
			log.info("Login Success");
			if (user1.getUsername().equals("admin")) {
				return "adhome";
			} else {
				return "redirect:/";
			}
		} else {
			if (userRepo.findByUsername(user1.getUsername()) != null) {
				result.rejectValue("password", "error.user", "password error,please enter password!");
			} else
				result.rejectValue("username", "error.user", "username error,please enter username!");
			return "login";
		}
	}
}