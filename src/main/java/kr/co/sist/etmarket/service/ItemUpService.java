package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemDao;
import kr.co.sist.etmarket.dao.ItemUpDao;
import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dto.ItemUpDto;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.ItemUp;
import kr.co.sist.etmarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class ItemUpService {

    private final ItemDao itemDao;
    private final ItemUpDao itemUpDao;
    private final UserDao userDao;

    @Autowired
    public ItemUpService(ItemDao itemDao, ItemUpDao itemUpDao, UserDao userDao) {
        this.itemDao = itemDao;
        this.itemUpDao = itemUpDao;
        this.userDao = userDao;
    }

    @Transactional
    public void upItem(ItemUpDto itemUpDto) {
        // itemId와 userId를 사용하여 Item과 User 엔티티를 조회
        Item item = itemDao.findById(itemUpDto.getItemId()).orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
        User user = userDao.findById(itemUpDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        // 끌어올리기 횟수 감소 로직 추가
        int remainingUpCount = decreaseRemainingUpCount(itemUpDto.getUserId());
        if (remainingUpCount >= 0) {
            ItemUp itemUp = new ItemUp();
            itemUp.setItem(item);
            itemUp.setUser(user);
            itemUp.setItemUpCount(itemUpDao.countByItem_ItemIdAndUser_UserId(itemUpDto.getItemId(), itemUpDto.getUserId()) + 1);
            itemUp.setItemUpDate(new Timestamp(System.currentTimeMillis()));
            itemUpDao.save(itemUp);
        } else {
            throw new IllegalArgumentException("끌어올리기 횟수가 부족합니다.");
        }
    }

    private int decreaseRemainingUpCount(Long userId) {
        // 끌어올리기 횟수 감소 로직 구현 (예: 데이터베이스에서 끌어올리기 횟수 관리)
        // 이 메서드는 사용자의 끌어올리기 횟수를 감소시키고 남은 횟수를 반환하는 로직을 구현해야 합니다.
        // 예를 들어 데이터베이스에서 사용자의 끌어올리기 횟수를 조회하고 감소시키는 등의 처리가 들어갑니다.
        return 5; // 예시로 최대 5번으로 설정
    }
}
