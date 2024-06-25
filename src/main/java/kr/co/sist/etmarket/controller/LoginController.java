package kr.co.sist.etmarket.controller;

import java.util.HashMap;
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

import com.mysql.cj.Session;

import jakarta.servlet.http.HttpSession;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	// 로그인폼 이동
	@GetMapping("/login")
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
	@PostMapping("/member/loginProcess")
	public String loginProcess(@ModelAttribute UserDto userDto, Model model, HttpSession session) {
		
		UserDto loginUser=loginService.login(userDto);
		
		if (loginUser!=null) {
			 // 로그인 성공
			UserDto loggedIn = loginUser; // UserDto에(loggedIn) 로그인된 계정 정보 추가
            model.addAttribute("user", loginUser); // userDto 객체를 모델에 추가
            
            session.setMaxInactiveInterval(60*60*8);
            session.setAttribute("myid", loggedIn.getUserLoginId());
			session.setAttribute("loginok", "yes");
			//session.setAttribute("saveok", cbsave); 추후 추가
            
            return "redirect:/"; 
        } else {
        	 // 로그인 실패 
            session.setAttribute("loginError", "아이디 및 비밀번호가 일치하지 않습니다."); // 에러 메시지를 세션에 추가
           
            return "redirect:/login"; 
        }
	}	
		
	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션 삭제
		session.removeAttribute("loginok");
		session.removeAttribute("myid");
		
		return "redirect:/";
	}
	
	// 아이디 찾기 폼
	@GetMapping("/member/find/loginId")
	public String goFindLoginId(HttpSession session, Model model) {
		
		// 이메일 전화번호가 일치하지 않을 경우, 세션에서 오류 메시지를 가져와 모델에 추가하고 세션에서 삭제
        String findIdPwError = (String) session.getAttribute("findIdPwError");
        if (findIdPwError != null) {
            model.addAttribute("findIdPwError", findIdPwError);
            session.removeAttribute("findIdPwError");
        }
		
		return "login/findLoginIdForm";
	}
	
	// 아이디 찾기 처리
	@PostMapping("/member/find/loginIdProcess")
	public String findLoginIdProcess(@RequestParam("userEmail") String userEmail, 
			@RequestParam("userPhone") String userPhone,
			Model model, HttpSession session) {
		//System.out.println("이메일: "+userEmail+", 전화번호: "+userPhone);
		
		UserDto loginId=loginService.findLoginId(userEmail, userPhone);
		
		//System.out.println("서비스에서 넘어온 값 출력: " + loginId);
		
		if(loginId!=null) {
			//System.out.println("회원정보 존재 시 if문: " + loginId);		
			model.addAttribute("loginId", loginId);
			
			return "login/findLoginId_Success";
		} else {
			//System.out.println("회원정보 없음..");
			session.setAttribute("findIdPwError", "입력정보와 일치하는 회원 정보가 없습니다."); // 에러 메시지를 세션에 추가
			
			return "redirect:/member/find/loginId";	
		}	
	}

	// 비밀번호 찾기
	@GetMapping("/member/find/password")
	public String goFindPw(HttpSession session, Model model) {   
		return "login/findPwForm";
	} 
	
	// 비밀번호 찾기 - 아이디&이메일 유무 체크 후 인증번호 보냄
	@PostMapping("/member/find/verifyRequest")
	@ResponseBody
	public Boolean verifyRequest(@RequestParam("userLoginId") String userLoginId, 
			@RequestParam("userEmail") String userEmail,
			Model model, HttpSession session) {
		//System.out.println("controller에서 비밀번호 찾기 유무체크: "+userLoginId+", "+userEmail);
		
		boolean chk=false;
		
		UserDto passwordUser=loginService.findPassword(userLoginId, userEmail);
		//System.out.println("로그인서비스에서 넘어온 값 출력: " + password);
		
		if(passwordUser!=null) {
			//System.out.println("true");
			chk=true;
			
			// 인증번호 보냄
			System.out.println("인증번호 보낸ㅁ");
			loginService.sendCodeToEmail(userEmail);
			
		} else {
			//System.out.println("false");
			chk=false;
		}	
	    return chk; 
	}
	
	// 비밀번호 찾기 - 인증번호 확인
	//@PostMapping("/member/find/verifyRequest")



}
