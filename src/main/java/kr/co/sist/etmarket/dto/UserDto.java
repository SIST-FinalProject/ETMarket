package kr.co.sist.etmarket.dto;

import java.sql.Timestamp;

import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.etenum.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	private Long userId;
	private String userLoginId;
	private String userPassword;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userImg;
	private Timestamp userCreateDate;
	private String userJoinType;
	private UserStatus userStatus;
    
    // User 엔티티를 UserDto로 변환하는 메소드
    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .userLoginId(user.getUserLoginId())
                .userPassword(user.getUserPassword())
                .userName(user.getUserName())
                .userPhone(user.getUserPhone())
                .userEmail(user.getUserEmail())
                .userImg(user.getUserImg())
                .userCreateDate(user.getUserCreateDate())
                .userJoinType(user.getUserJoinType())
                .userStatus(user.getUserStatus())
                .build();
    }
    
}
