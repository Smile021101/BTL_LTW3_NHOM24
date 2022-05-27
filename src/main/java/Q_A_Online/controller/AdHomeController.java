package Q_A_Online.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdHomeController {

	@GetMapping("/adhome")
	public String showAdminHome() {
		return "adhome";
	}
}
