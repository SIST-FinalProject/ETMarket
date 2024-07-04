package kr.co.sist.etmarket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(columnDefinition = "TEXT")
    private String message;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp sendDate;

    private String sender;

    private Long senderId;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    private String img;

    private String chatRead; // 읽은 여부

    @Builder
    public Message(ChatRoom chatRoom, String sender, Long senderId, String message) {
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.senderId = senderId;
        this.sendDate = Timestamp.valueOf(LocalDateTime.now());
        this.message = message;
    }

    //== 연관관계 메소드 ==//

    /**
     * 채팅 생성
     */
    public static Message createMessage(ChatRoom chatRoom, String sender, Long senderId, String message) {
        return Message.builder()
                .chatRoom(chatRoom)
                .sender(sender)
                .senderId(senderId)
                .message(message)
                .build();
    }


}