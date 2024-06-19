package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemDto;
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
        UserSearch userSearch = userSearchDao.findByContent(userSearchDto.getContent());
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
            ItemDto item = new ItemDto((Integer) ob[0],
                    (Integer) ob[1],
                    ((Number) ob[2]).longValue(),
                    (Timestamp) ob[3],
                    (Timestamp) ob[4],
                    ((Number) ob[5]).longValue(),
                    (String) ob[6],
                    (String) ob[7],
                    (String) ob[8],
                    (CategoryName) ob[9],
                    (DealHow) ob[10],
                    (DealStatus) ob[11],
                    (DeliveryStatus) ob[12],
                    (ItemHidden) ob[13],
                    (ItemStatus) ob[14],
                    (PriceStatus) ob[15],
                    (Integer) ob[16],
                    ((Number) ob[17]).longValue()
                    );

            items.add(item);
        }
        return items;
    }

}
