package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ChatRoomDao;
import kr.co.sist.etmarket.dto.ChatRoomCountDto;
import kr.co.sist.etmarket.entity.ChatRoom;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomDao chatRoomDao;

    
    /*마이페이지에서 사용*/
    public List<ChatRoomCountDto> getChatRoomCountByItemId() {
        List<Object[]> results = chatRoomDao.countChatRoomsByItemId();
        return results.stream()
                .map(result -> new ChatRoomCountDto(
                        (Long) result[0],
                        (Long) result[1]
                ))
                .collect(Collectors.toList());
    }

    public List<User> getChatParticipantNamesByItemId(Long itemId) {
        List<User> participantIds = new ArrayList<>();
        List<ChatRoom> chatRooms = chatRoomDao.findByItem_ItemId(itemId); // Item ID로 조회하도록 수정
        for (ChatRoom chatRoom : chatRooms) {
            participantIds.add(chatRoom.getSender()); // 채팅에 참여한 유저 객체 가져오기 (senderId)
        }
        return participantIds;
    }

    public Long getSellerIdByItemId(Long itemId) {
        ChatRoom chatRoom = chatRoomDao.findByItem_ItemId(itemId).stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Chat room not found for item ID"));
        return chatRoom.getReceiver().getUserId(); // 판매자 ID 반환
    }
}
