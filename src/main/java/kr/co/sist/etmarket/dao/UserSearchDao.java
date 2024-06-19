package kr.co.sist.etmarket.dao;


import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.entity.UserSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserSearchDao extends JpaRepository<UserSearch, java.lang.Long> {

    // 유저에 대한 최근 검색 날짜 desc 8개 출력
    List<UserSearch> findTop8ByUserOrderByUpdateDateDesc(User user);

    // 검색한 유저 찾기
    List<UserSearch> findByUser(User user);

    // 유저에 대한 content 값이 존재하면 updateDate 수정 => 최근 검색어를 클릭 시 상단으로 이동시키기 위함
    Optional<UserSearch> findByUserAndContent(User user, String content);

    // 인기 검색어 8개 출력
    @Query(value = "SELECT content, COUNT(content) AS WORDCNT " +
            "FROM user_search " +
            "GROUP BY content " +
            "ORDER BY WORDCNT desc " +
            "LIMIT 8", nativeQuery = true)
    List<Object[]> findTop8PopularContent();

    // 검색어 하나 가져오기
    UserSearch findByContent(String content);

    // 검색어에 맞는 상품 리스트 출력
    @Query(value = "SELECT i.* FROM item i LEFT JOIN user_search us USING(user_search_id) WHERE i.item_title LIKE %:content%", nativeQuery = true)
    List<Object[]> findItemsByContentAndItemTitle(@Param("content") String content);
    
    
}
