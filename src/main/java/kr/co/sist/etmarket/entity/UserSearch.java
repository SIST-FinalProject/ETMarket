package kr.co.sist.etmarket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSearchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp resistDate;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp updateDate;

    @Override
    public String toString() {
        return "UserSearch{" +
                "userSearchId=" + userSearchId +
                ", user=" + user.getUserId() +
                ", content='" + content + '\'' +
                ", resistDate=" + resistDate +
                ", updateDate=" + updateDate +
                '}';
    }

}
