package Q_A_Online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsHomeController {

	@GetMapping("/ushome")
	public String showAdminHome() {
		return "ushome";
	}
}
