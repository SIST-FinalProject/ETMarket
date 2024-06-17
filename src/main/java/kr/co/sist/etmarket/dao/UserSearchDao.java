package kr.co.sist.etmarket.dao;


import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.entity.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSearchDao extends JpaRepository<UserSearch, Long> {

    // 유저에 대한 최근 검색 날짜 desc 8개 출력
    List<UserSearch> findTop8ByUserOrderByUpdateDateDesc(User user);

    // 검색한 유저 찾기
    List<UserSearch> findByUser(User user);

    // 유저에 대한 content 값이 존재하면 updateDate 수정
    Optional<UserSearch> findByUserAndContent(User user, String content);




}
