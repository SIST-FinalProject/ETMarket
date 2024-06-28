package kr.co.sist.etmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;

@Service
public class JoinService {
	
	@Autowired
	UserDao userDao;
	
	// 회원가입
    public void join(UserDto userDto) {
        //System.out.println("joinService에서 userDto 출력: " + userDto);

        // UserDto를 User 엔티티로 변환
        User user = userDto.toEntity();
        
        // User 엔티티를 저장
        userDao.save(user);
    }

}
