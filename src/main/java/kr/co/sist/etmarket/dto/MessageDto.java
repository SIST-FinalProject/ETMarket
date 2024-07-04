package kr.co.sist.etmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDto {

    private int messageId;

    private String message;

    private Timestamp sendTime;

    private String sender;

//    private String receiver;

    private ChatRoomDto chatRoom;

    private String chatroomId;

    private String img;

    private String chatRead; // 읽은 여부

    private MessageType type;

    public enum MessageType {
        // 입장, 채팅, 나감
        ENTER, TALK, QUIT
    }

}