package Q_A_Online.controller;

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
@RequestMapping("/register")
public class RegisterController {
	private final UserRepository userRepo;

	@Autowired
	public RegisterController(UserRepository userRepo) {

		this.userRepo = userRepo;

	}

	@GetMapping
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping
	public String addUser(@Valid User user,BindingResult result, Model model) {
		//log.info("1");
		if(result.hasErrors()) {
			return "register";
		}
		if (userRepo.findByUsername(user.getUsername())==null) {
			log.info("1");
			userRepo.save(user);
			model.addAttribute(user);
			return "redirect:/login";
		}
		else{
			result.rejectValue("username", "error.user","username is existed!");
			return "register";
		}
	}
	@RequestMapping("/")
	public String CancelLogin() {
		return "redirect:/";
	}
}