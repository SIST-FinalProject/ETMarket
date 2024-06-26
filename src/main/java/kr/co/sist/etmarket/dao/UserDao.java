package kr.co.sist.etmarket.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sist.etmarket.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	// 로그인
	User findByUserLoginId(String userLoginId);

	// 아이디 찾기
	User findByUserPhoneAndUserEmail(String userPhone, String userEmail);
	
	// 비밀번호 찾기
	User findByUserLoginIdAndUserEmail(String userLoginId, String userEmail);

}
