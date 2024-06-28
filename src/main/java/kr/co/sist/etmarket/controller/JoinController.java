package kr.co.sist.etmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {

	@GetMapping("/member/join")
	public String goJoin() {
		return "join/joinForm";
	}
}
