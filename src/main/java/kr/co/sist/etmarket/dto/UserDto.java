package kr.co.sist.etmarket.dto;

import java.sql.Timestamp;

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

	// 생성자 - 로그인
    public UserDto(String userLoginId, String userPassword, Long userId, String userName) {
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userId = userId;
        this.userName = userName;
    }
}
