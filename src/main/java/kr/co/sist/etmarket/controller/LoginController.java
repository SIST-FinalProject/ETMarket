package kr.co.sist.etmarket.controller;

import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String goLogin(HttpSession session, Model model) {
		
		// 아이디 비밀번호가 일치하지 않을 경우, 세션에서 오류 메시지를 가져와 모델에 추가하고 세션에서 삭제
        String loginError = (String) session.getAttribute("loginError");
        if (loginError != null) {
            model.addAttribute("loginError", loginError);
            session.removeAttribute("loginError");
        }
        
    	return "login/loginForm";
    }
	
	// 로그인 처리
	@PostMapping("loginProcess")
	public String loginprocess(@ModelAttribute UserDto userDto, Model model, HttpSession session) {
		
		Optional<UserDto> loginUser=loginService.login(userDto);
		
		if (loginUser.isPresent()) {
			 // 로그인 성공
			UserDto loggedIn = loginUser.get(); // UserDto에(loggedIn) 로그인된 계정 정보 추가
            model.addAttribute("user", loginUser.get()); // userDto 객체를 모델에 추가
            
            session.setMaxInactiveInterval(60*60*8);
            session.setAttribute("myid", loggedIn.getUserLoginId());
			session.setAttribute("loginok", "yes");
			//session.setAttribute("saveok", cbsave);
            
            return "redirect:/"; 
        } else {
        	 // 로그인 실패 
            session.setAttribute("loginError", "아이디 및 비밀번호가 일치하지 않습니다."); // 에러 메시지를 세션에 추가
           
            return "redirect:login"; 
        }
	}

	// 아이디 찾기 프로세스 ajax 처리
	@PostMapping("/findLoginIdProcess")
	@ResponseBody
	public String findloginid(String userEmail, String userPhone, HttpSession session) {
		Optional<UserDto> findLoginId=loginService.findByLoginId(userEmail, userPhone);
		return null;
	}
		
		
	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		// 세션 삭제
		session.removeAttribute("loginok");
		session.removeAttribute("myid");
		
		return "redirect:/";
	}
	
	// 아이디 찾기 폼
	@GetMapping("findLoginId")
	public String findid() {
		return "login/findLoginId";
	}
	
	
	
	
	// ======================================================
	// 비밀번호 찾기
	@GetMapping("findPw")
	public String findpw() {
		return "login/findPw";
	}

}
