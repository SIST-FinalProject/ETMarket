package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dao.UserSearchDao;
import kr.co.sist.etmarket.dto.UserSearchDto;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.entity.UserSearch;
import kr.co.sist.etmarket.etenum.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserSearchService {

    @Autowired
    private UserSearchDao userSearchDao;
    @Autowired
    private UserDao userDao;

    // UserSearch insert
    public void insertContent(UserSearchDto userSearchDto) {
        UserSearch userSearch = userSearchDao.findByContent(userSearchDto.getContent()).get(0);
        User user = userDao.findById(userSearchDto.getUserId()).get();
        if (userSearch == null) {
            userSearch = new UserSearch();
            userSearch.setContent(userSearchDto.getContent());
            userSearch.setUser(user);
        }
        userSearchDao.save(userSearch);
    }

    // 인기 검색어 8개 출력
    public List<UserSearchDto> getTop8PopularContent() {
        List<Object[]> result = userSearchDao.findTop8PopularContent();
        return result.stream()
                .map(userSearch -> new UserSearchDto((String) userSearch[0]))
                .collect(Collectors.toList());
    }

    // 유저에 대한 최근 검색 날짜 desc 8개 출력
    public List<UserSearchDto> getTop8SearchContent(UserSearchDto userSearchDto) {
        User user = userDao.findById(userSearchDto.getUserId()).get();
        List<UserSearch> result = userSearchDao.findTop8ByUserOrderByUpdateDateDesc(user);
        return result.stream()
                .map(userSearch -> new UserSearchDto(userSearchDto.getUserId(), userSearch.getContent(),userSearch.getUpdateDate()))
                .collect(Collectors.toList());
    }

    // 최근 검색어를 클릭 시 상단으로 이동시키기 위함
    public void getUserSearchUpdate(User user, String content) {
        Optional<UserSearch> existing = userSearchDao.findByUserAndContent(user, content);
        if (existing.isPresent()) { // 기존의 UserSearch 객체가 존재하는 경우
            UserSearch userSearch = existing.get(); // 기존 객체를 가져옴
            // resistDate를 현재 시간으로 업데이트
            userSearch.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
            // 업데이트된 객체를 데이터베이스에 저장
            userSearchDao.save(userSearch);
        }
    }
    
    // 검색어에 대한 상품 리스트
    //List<Object[]> findItemsByContentAndItemTitle(@Param("content") String content);
    public List<ItemDto> getItemTitle(String content) {
        List<Object[]> results = userSearchDao.findItemsByContentAndItemTitle(content);
        List<ItemDto> items = new ArrayList<>();

        for (Object[] ob : results) {
            ItemDto item = new ItemDto(
//                    ob[0] != null ? (Integer) ob[0] : 0,
                    ob[1] != null ? (Integer) ob[1] : 0, // itemPrice
                    ob[2] != null ? ((Number) ob[2]).longValue() : 0L, // itemId
//                    (Timestamp) ob[3],
                    (Timestamp) ob[4], // updateDate
                    ob[5] != null ? ((Number) ob[5]).longValue() : 0L, // userId
                    ob[6] != null ? (String) ob[6] : "", // itemAddress
//                    ob[7] != null ? (String) ob[7] : "",
                    ob[8] != null ? (String) ob[8] : "", // itemTitle
//                    (CategoryName) ob[9],
//                    (DealHow) ob[10],
                    ob[11] != null ? DealStatus.valueOf((String) ob[11]) : DealStatus.판매중, // 거래 여부 상태
                    ob[12] != null ? DeliveryStatus.valueOf((String) ob[12]) : DeliveryStatus.비포함, // 배송비포함 비포함
                    ob[13] != null ? ItemHidden.valueOf((String) ob[13]) :ItemHidden.보임 // 아이템 숨김 보임
//                    (ItemStatus) ob[14],
//                    (PriceStatus) ob[15],
//                    ob[16] != null ? (Integer) ob[16] : 0,
//                    ob[17] != null ? ((Number) ob[17]).longValue() : 0L
            );

            items.add(item);
        }
        return items;
    }

    public List<UserSearch> getContent(String content) {
        return userSearchDao.findByContent(content);
    }

    public void deleteContent(String content) {
        List<UserSearch> results = userSearchDao.findByContent(content);
        for (UserSearch entity : results) {
            userSearchDao.delete(entity);
        }
    }

}
