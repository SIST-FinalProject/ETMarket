package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ChatRoom;
import kr.co.sist.etmarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomDao extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findBySender(User sender);

    ChatRoom findByItem_ItemIdAndSender_UserId(Long itemId, Long senderId);


}