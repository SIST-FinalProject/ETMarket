package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ChatRoomDao;
import kr.co.sist.etmarket.dto.ChatRoomDto;
import kr.co.sist.etmarket.entity.ChatRoom;
import kr.co.sist.etmarket.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomDao chatRoomDao;

    public void save(ChatRoom chatRoom) {
        chatRoomDao.save(chatRoom);
    }

    public boolean findChatRoomByItemIdAndSenderId(Long itemId, Long senderId) {
        ChatRoom chatRoom = chatRoomDao.findByItem_ItemIdAndSender_UserId(itemId, senderId);

        ChatRoomDto chatRoomDto = convertToDto(chatRoom);
        if (chatRoomDto != null) {
            return true;
        } else {
            return false;
        }

    }

    public ChatRoom getChatroomId(Long chatroomId) {
        return chatRoomDao.findById(chatroomId).get();
    }

    public List<ChatRoomDto> findAllBySender(User sender) {
        List<ChatRoom> chatRooms =  chatRoomDao.findBySender(sender);
        return chatRooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ChatRoomDto> findAllRoomName() {
        List<ChatRoom> chatRooms = chatRoomDao.findAll();

        return chatRooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ChatRoomDto convertToDto(ChatRoom chatRoom) {
        if (chatRoom == null) {
            return null;
        }
        return ChatRoomDto.builder()
                .chatroomId(chatRoom.getChatroomId())
                .createDate(chatRoom.getCreateDate())
                .itemId(chatRoom.getItem().getItemId())
                .senderId(chatRoom.getSender().getUserId())
                .receiverId(chatRoom.getReceiver().getUserId())
                .messages(chatRoom.getMessages())
                .chatroomImg(chatRoom.getChatroomImg())
                .build();
    }

}
