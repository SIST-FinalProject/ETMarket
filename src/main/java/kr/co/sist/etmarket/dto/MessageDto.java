package kr.co.sist.etmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDto {

    private Long messageId;

    private String content;

    private Timestamp sendTime;

    private UserDto sender;

    private UserDto receiver;

    private ChatRoomDto chatRoom;

    private String img;

    private String chatRead; // 읽은 여부

}
