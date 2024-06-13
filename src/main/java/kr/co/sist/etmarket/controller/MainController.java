package kr.co.sist.etmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String start(){
        return "main/main";
    }
    
    @GetMapping("login/login")
    public String goLogin() {
    	return "login/loginForm";
    }
}
