package kr.co.sist.etmarket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	// 로그인폼 이동
	@GetMapping("login")
    public String goLogin() {
    	return "login/loginForm";
    }
	
	// 로그인 처리
	@PostMapping("loginprocess")
	public String loginprocess(@ModelAttribute UserDto userDto, Model model, HttpSession session) {
		
		Optional<UserDto> loginUser=loginService.login(userDto);
		
		if (loginUser.isPresent()) {
			 // 로그인 성공
			UserDto loggedInUser = loginUser.get(); // UserDto에 로그인된 계정 정보 추가
            model.addAttribute("user", loginUser.get()); // userDto 객체를 모델에 추가
            
            session.setMaxInactiveInterval(60*60*8);
            session.setAttribute("myid", loggedInUser.getUserLoginId());
			session.setAttribute("loginok", "yes");
			//session.setAttribute("saveok", cbsave);
			
			// 로그인 정보 확인용
//			System.out.println("아이디 출력: "+loggedInUser.getUserLoginId());
//			System.out.println("이름 출력: "+loggedInUser.getUserName());
//			System.out.println("회원번호 출력: "+loggedInUser.getUserId());
            
            return "redirect:/"; 
        } else {
        	 // 로그인 실패 
            model.addAttribute("loginError", "아이디 및 비밀번호가 일치하지 않습니다."); // 에러 메시지를 모델에 추가
           
            return "login/loginForm"; 
        }
	}
	
	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		// 세션 삭제
		session.removeAttribute("loginok");
		session.removeAttribute("myid");
		
		return "redirect:/";
	}

}
