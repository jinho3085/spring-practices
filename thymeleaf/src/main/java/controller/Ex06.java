package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ex06")
public class Ex06 {
	@GetMapping("/01")
	public String _01(Model model) {
		model.addAttribute("text", "차례로 돌아가기");
		return "ex06/01";
	}
}