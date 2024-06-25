package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<Item, Long> {
    // itemId값에 따른 getData
    Item findByItemId(Long itemId);
}
