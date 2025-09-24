package controller;

import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ex04")
public class Ex04 {
	@GetMapping("/01")
	public String _01(Model model) {
		model.addAttribute("list", Arrays.asList(new UserVo(1L, "둘리1"), new UserVo(2L, "둘리2"), new UserVo(3L, "둘리3")));
		return "ex04/01";
	}

	@GetMapping("/02")
	public String _02(
		@RequestParam(value="r", required=true, defaultValue="0") Integer row,
		@RequestParam(value="c", required=true, defaultValue="0") Integer col,
		Model model) {
		model.addAttribute("row", row);
		model.addAttribute("col", col);
		return "ex04/02";
	}
}