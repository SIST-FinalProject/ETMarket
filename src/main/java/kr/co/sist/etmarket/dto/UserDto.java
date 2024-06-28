package kr.co.sist.etmarket.dto;

import kr.co.sist.etmarket.etenum.UserStatus;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    private String userSocialToken;

    private UserStatus userStatus;

    private List<ItemDto> items = new ArrayList<>();

//    private List<ItemLike> itemLikes = new ArrayList<>();

    private List<UserSearchDto> userSearches = new ArrayList<>();

//    private List<Deal> sellers = new ArrayList<>();
//
//    private List<Deal> buyers = new ArrayList<>();
//
//    private List<Rating> target = new ArrayList<>();

    // 생성자 - 로그인
    public UserDto(String userLoginId, String userPassword, Long userId, String userName) {
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userId = userId;
        this.userName = userName;
    }
}
