package controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ex01")
public class Ex01 {
	
	@GetMapping({"", "/01"})
	public String _01() {
		// thymeleaf view
		return "ex01/01";
	}

	@GetMapping("/02")
	public String _02() {
		// jstl view
		return "views/ex01/02";
	}
	
	@GetMapping("/03")
	public String _03(Model model) {
		model.addAttribute("iVal", 10);
		model.addAttribute("lVal", 10);
		model.addAttribute("fVal", 3.14f);
		model.addAttribute("bVal", true);
		model.addAttribute("sVal", "<strong>가나다라마바사</strong>");
		model.addAttribute("obj", null);
		model.addAttribute("userVo", new UserVo(10L, "둘리"));
		model.addAttribute("m", Map.of("iVal", 10, "fVal", 10, "sVal", "가나다라마바사"));
		
		return "ex01/03";
	}
}
