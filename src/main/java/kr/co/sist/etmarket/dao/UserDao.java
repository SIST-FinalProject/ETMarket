package kr.co.sist.etmarket.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	// 로그인
	@Query("SELECT new kr.co.sist.etmarket.dto.UserDto(u.userLoginId, u.userPassword, u.userId, u.userName) FROM User u WHERE u.userLoginId = :userLoginId")
	Optional<UserDto> findByUserLoginId(@Param("userLoginId") String userLoginId);

}
