package cz.kojotak.msaa.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String show(Model model) {
		model.addAttribute("message", "hello world");
		return "test";
	}
	
}
