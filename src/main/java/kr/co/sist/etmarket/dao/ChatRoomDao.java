package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.dto.ChatRoomCountDto;
import kr.co.sist.etmarket.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomDao extends JpaRepository<ChatRoom, Long> {

    /* 마이페이지에서 사용 */
    @Query("SELECT c.item.itemId, COUNT(c.chatroomId) AS ChatRoomCount FROM ChatRoom c GROUP BY c.item.itemId")
    List<Object[]> countChatRoomsByItemId();

    List<ChatRoom> findByItem_ItemId(Long itemId);

}
