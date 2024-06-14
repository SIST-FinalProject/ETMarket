package kr.co.sist.etmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@GetMapping("login")
    public String goLogin() {
    	return "login/loginForm";
    }
	
	@PostMapping("loginprocess")
	public String loginpro(String userLoginId, String userPassword) {
		
		loginService.login(userLoginId, userPassword);
		
		return "";
	}

}
