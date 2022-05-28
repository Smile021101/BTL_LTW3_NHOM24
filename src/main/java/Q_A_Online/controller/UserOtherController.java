package Q_A_Online.controller;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Q_A_Online.model.User;
import Q_A_Online.repository.UserRepository;

@Controller
@RequestMapping("/user_other")
public class UserOtherController {
	private UserRepository userRepo;
	public UserOtherController(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	@GetMapping("/{id}")
	public String showUserOrtherForm(@PathVariable Long id, Model model, HttpSession session) {
		Optional<User> user = userRepo.findById(id);
		model.addAttribute("user",user.get());
		User userr = (User) session.getAttribute("currentUser");
		if(user.get().getUsername().equals(userr.getUsername())) {
			return "ushome";
		}
		return "user_other";
	}
	
	@PostMapping
	public String returnHome(Model model) {
		return "redirect:/";
	}
}