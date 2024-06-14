package kr.co.sist.etmarket.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sist.etmarket.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	Optional<User> findByUserLoginId(String userLoginId);
}
