package kr.co.sist.etmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.etenum.UserStatus;
import kr.co.sist.etmarket.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	// 회원정보 수정 페이지로 이동
	@GetMapping("/seller/mypage")
	public ModelAndView goMypage(@RequestParam("userId") Long userId) {
		ModelAndView mview=new ModelAndView();
		
		User userInfo=userService.getUserById(userId);
		mview.addObject("userInfo", userInfo);
		
		mview.setViewName("myPage/mypageEdit");
		return mview;
	}
	
	// 회원정보 수정
//	@PostMapping("/member/update")
//	public String updateUser(@ModelAttribute UserDto userDto, @RequestParam("userImgUpload") MultipartFile userImgUpload) {
//		System.out.println("userController 수정될 값: "+userDto+", 이미지: "+userImgUpload);
//		
//		return "redirect:/login";
//	}
	@PostMapping("/member/update")
	public String updateUser(@ModelAttribute UserDto userDto) {
		System.out.println("userController 수정될 값: "+userDto);
		userService.update(userDto);
		
		return "redirect:/login";
	}
	
	// 회원탈퇴
	@GetMapping("/member/delete")
	public String deleteUser(@RequestParam("userId") Long userId, UserDto userDto) {	
		userService.delete(userId);
		
		return "redirect:/";
	}
}
