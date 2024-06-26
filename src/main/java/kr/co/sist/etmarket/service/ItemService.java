package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.dao.ItemDao;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.etenum.DealStatus;
import kr.co.sist.etmarket.etenum.ItemHidden;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemDao itemDao;

    /*public Page<Item> findByUserId(Long userId, Pageable pageable) {
        return itemDao.findByUserId(userId, pageable);
    }*/

    public Page<Item> itemList(Pageable pageable){
        return itemDao.findAll(pageable);
    }

    public Page<Item> itemSearchList(String keyword, Pageable pageable){
        return itemDao.findByItemTitleContaining(keyword, pageable);
    }

    public Page<Item> itemStatusList(DealStatus dealStatus, Pageable pageable) {
        return itemDao.findByDealStatus(dealStatus, pageable);
    }

    public Page<Item> itemSearchAndStatusList(String keyword, DealStatus dealStatus, Pageable pageable) {
        return itemDao.findByItemTitleAndDealStatus(keyword, dealStatus, pageable);
    }

    public void updateItemStatus(ItemDto itemDto) {
        Item item = itemDao.findById(itemDto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
        item.setDealStatus(itemDto.getDealStatus());
        itemDao.save(item);
    }



    @Transactional
    public boolean updateHiddenStatus(Long itemId, ItemHidden hidden) {
        Optional<Item> optionalItem = itemDao.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setItemHidden(hidden);
            itemDao.save(item);
            return true;
        } else {
            return false;
        }
    }

    public Page<Item> findByItemHidden(ItemHidden hidden, Pageable pageable) {
        return itemDao.findByItemHidden(hidden, pageable);
    }

    public Page<Item> getVisibleItems(DealStatus dealStatus, Pageable pageable) {
        return itemDao.findByItemHiddenAndDealStatus(ItemHidden.보임, DealStatus.valueOf(dealStatus.name()), pageable);
    }

}
