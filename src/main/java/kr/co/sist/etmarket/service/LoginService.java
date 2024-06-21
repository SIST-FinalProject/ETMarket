package kr.co.sist.etmarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	@Autowired
	UserDao userDao;
	
	// 로그인
	public UserDto login(UserDto userDto) {
		// user에서 userLoginId 값 가져옴
		User findUser = userDao.findByUserLoginId(userDto.getUserLoginId());
		
		// 아이디가 존재하는지 확인하고, 비밀번호가 일치하는지 확인
		if (findUser!=null && findUser.getUserPassword().equals(userDto.getUserPassword())) {
			//System.out.println("LoginService에서 유저정보 출력:"+findUser);
			return UserDto.fromEntity(findUser);
		}

        return null;
    }
	
	// 아이디 찾기
	public UserDto findLoginId(String userEmail, String userPhone){
		User findLoginId = userDao.findByUserPhoneAndUserEmail(userPhone, userEmail);
		
		if(findLoginId!=null) {
			//System.out.println("로그인 아이디 서비스에서 출력:"+findLoginId);			
			return UserDto.fromEntity(findLoginId);
		}			
		return null;
	}
	
	// 비밀번호 찾기
	public UserDto findPassword(String userLoginId, String userPhone) {
		User findPassword=userDao.findByUserLoginIdAndUserEmail(userLoginId, userPhone);
		
		if(findPassword!=null) {
			System.out.println("service 출력 - "+findPassword);
			return UserDto.fromEntity(findPassword);
		}	
		return null;
	}
	
	//

}
