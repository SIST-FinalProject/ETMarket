package kr.co.sist.etmarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	@Autowired
	UserDao userDao;
	
	// 로그인
	public Optional<User> login(String userLoginId, String userPassword) {
        User findUser = userDao.findByUserLoginId(userLoginId).get();
        
        // 0614 작업중
        return null;
    }

}
