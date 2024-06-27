package kr.co.sist.etmarket.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	// 로그인
	User findByUserLoginId(String userLoginId);

	// 아이디 찾기
	User findByUserPhoneAndUserEmail(String userPhone, String userEmail);
	
	// 비밀번호 찾기
	User findByUserLoginIdAndUserEmail(String userLoginId, String userEmail);
	
	// 비밀번호 재설정
	@Query(value="update user set user_password=:userPassword where user_login_id=:userLoginId AND user_email=:userEmail", nativeQuery = true)
	@Modifying
	@Transactional
	public void updateUserPassword(@Param("userLoginId") String userLoginId, @Param("userEmail") String userEmail, @Param("userPassword") String userPassword);

}
