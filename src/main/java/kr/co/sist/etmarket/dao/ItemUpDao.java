package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ItemUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemUpDao extends JpaRepository<ItemUp, Long> {

    int countByItem_ItemIdAndUser_UserId(Long itemId, Long userId);
}
