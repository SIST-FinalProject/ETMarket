package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.etenum.DealStatus;
import kr.co.sist.etmarket.etenum.ItemHidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDao extends JpaRepository<Item, Long> {

   /*Page<Item> findByUserId(Long userId, Pageable pageable);*/

   Page<Item> findByItemTitleContaining(String keyword, Pageable pageable);

   Page<Item> findByDealStatus(DealStatus dealStatus, Pageable pageable);

   Page<Item> findByItemTitleAndDealStatus(String keyword, DealStatus dealStatus, Pageable pageable);

   Page<Item> findByItemHidden(ItemHidden hidden, Pageable pageable);

   Page<Item> findByItemHiddenAndDealStatus(ItemHidden hidden, DealStatus dealStatus, Pageable pageable);
}
