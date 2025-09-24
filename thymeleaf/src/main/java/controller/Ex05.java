package controller;

import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ex05")
public class Ex05 {
	@GetMapping("/01")
	public String _01(Model model) {
		model.addAttribute("users", Arrays.asList(new UserVo(1L, "둘리", "male"), new UserVo(2L, "마이콜", "male"), new UserVo(3L, "영희", "female"), new UserVo(4L, "김정자", "female")));
		return "ex05/01";
	}

	@GetMapping("/02")
	public String _02() {
		return "ex05/02";
	}
}