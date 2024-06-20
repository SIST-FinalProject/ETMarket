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
		
		Optional<UserDto> loginUser=loginService.login(userDto);
		
		if (loginUser.isPresent()) {
			 // 로그인 성공
			UserDto loggedIn = loginUser.get(); // UserDto에(loggedIn) 로그인된 계정 정보 추가
            model.addAttribute("user", loginUser.get()); // userDto 객체를 모델에 추가
            
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
		
		// 아이디 비밀번호가 일치하지 않을 경우, 세션에서 오류 메시지를 가져와 모델에 추가하고 세션에서 삭제
        String findIdPwError = (String) session.getAttribute("findIdPwError");
        if (findIdPwError != null) {
            model.addAttribute("findIdPwError", findIdPwError);
            session.removeAttribute("findIdPwError");
        }
		
		return "login/findLoginIdForm";
	}
	
	@PostMapping("/member/find/loginIdProcess")
	@ResponseBody
	public Map<String, Object> findLoginIdProcess(@RequestParam("userEmail") String userEmail, @RequestParam("userPhone") String userPhone) {
	    Map<String, Object> response = new HashMap<>();

	    System.out.println("controller 출력 이메일: " + userEmail + " 전화번호: " + userPhone);

	    Optional<UserDto> loginId = loginService.findLoginId(userEmail, userPhone);

	    System.out.println("서비스에서 넘어온 값 출력: " + loginId);

	    if (loginId.isPresent()) {
	        System.out.println("회원정보 존재 시 if문: " + loginId.get());
	        response.put("status", "success");
	        response.put("user", loginId.get());
	    } else {
	        System.out.println("회원정보 없음");
	        response.put("status", "error");
	        response.put("message", "입력정보와 일치하는 회원 정보가 없습니다.");
	    }

	    return response;
	}

	
//	// 아이디 찾기 프로세스 ajax 처리
//	@PostMapping("/member/find/findLoginIdProcess")
//	@ResponseBody
//	public UserDto findloginid(@RequestParam("userEmail") String userEmail, @RequestParam("userPhone") String userPhone) {
//		
//	    Optional<UserDto> loginId = loginService.findLoginId(userEmail, userPhone);
//	    
//	    if (loginId.isEmpty()) {
//	    	return null;
//	    } else {
//	    	UserDto findLoginId = loginId.get();
//	    	//model.addAttribute("userLoginId", findLoginId.getUserLoginId());
//	    	System.out.println("아이디 찾기 성공"+findLoginId.getUserLoginId());
//	        
//	    	return findLoginId;
//	    }	    
//	}
//	@PostMapping("/member/find/findLoginIdProcess")
//	@ResponseBody
//	public ResponseEntity<Map<String, Object>> findloginid(@RequestParam("userEmail") String userEmail, @RequestParam("userPhone") String userPhone) {
//	    Map<String, Object> response = new HashMap<>();
//	    
//	    System.out.println("Received userEmail: " + userEmail);
//	    System.out.println("Received userPhone: " + userPhone);
//
//	    Optional<UserDto> loginId = loginService.findLoginId(userEmail, userPhone);
//
//	    if (loginId.isPresent()) {
//	        UserDto findLoginId = loginId.get();
//	        response.put("status", "success");
//	        response.put("userLoginId", findLoginId.getUserLoginId());
//	        System.out.println("아이디 찾기 성공: " + findLoginId.getUserLoginId());
//	    } else {
//	        response.put("status", "fail");
//	        response.put("message", "입력정보와 일치하는 회원 정보가 없습니다.");
//	        System.out.println("아이디 찾기 실패: 입력정보와 일치하는 회원 정보가 없습니다.");
//	    }
//
//	    return ResponseEntity.ok(response);
//	}


	
	
	
	
	// ======================================================
	// 비밀번호 찾기
	@GetMapping("/member/find/password")
	public String findpw() {
		return "login/findPw";
	}

}
