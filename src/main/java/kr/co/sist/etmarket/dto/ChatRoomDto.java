package kr.co.sist.etmarket.dto;

import kr.co.sist.etmarket.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRoomDto {

    private Long chatroomId;

    private String roomName;

    private Timestamp createDate;

    private Item item; // 상품 id

    private UserDto sender; // 최초 메세지 보낸 사람 id

    private UserDto receiver; // 최초 메제지 받는 사람 id

    private List<MessageDto> messages = new ArrayList<>();

    private String chatroomImg;
}
