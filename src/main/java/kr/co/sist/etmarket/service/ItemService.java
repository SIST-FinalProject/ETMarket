package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemDao;
import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.etenum.DealStatus;
import kr.co.sist.etmarket.etenum.ItemHidden;
import kr.co.sist.etmarket.etenum.PriceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemDao itemDao;
    private final UserDao userDao;

    // Item DB insert
    public Item insertItem(ItemDto itemDto) {
        //ItemDto 가공후 Entity로 변환
        Item item = processInsertItemDto(itemDto);

        // Item DB insert
        return itemDao.save(item);
    }

    // itemDto insert를 위해 가공 후 Item Entity로 변환
    public Item processInsertItemDto(ItemDto itemDto) {
        // itemDto 가공
        itemDto.setItemPrice(Integer.parseInt(itemDto.getItemPriceText().replace(",","")));
        if (itemDto.getDetailAddress().isBlank()) {
            itemDto.setItemAddress(itemDto.getRoadAddress());
        } else {
            itemDto.setItemAddress(itemDto.getRoadAddress() + " (" + itemDto.getDetailAddress() + ")");
        }
        itemDto.setDealStatus(DealStatus.예약중);
        itemDto.setItemDeliveryPrice(Integer.parseInt(itemDto.getItemDeliveryPriceText().replace(",","")));
        if (itemDto.isPriceStatusCheck()) {
            itemDto.setPriceStatus(PriceStatus.가능);
        } else {
            itemDto.setPriceStatus(PriceStatus.불가능);
        }
        itemDto.setItemCount(Integer.parseInt(itemDto.getItemCountText().replace(",","")));
        itemDto.setItemHidden(ItemHidden.보임);

        // Item Entity로 변환
        User user = userDao.findById(itemDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("없는 user ID"));

        return Item.builder()
                .user(user)
                .itemTitle(itemDto.getItemTitle())
                .itemContent(itemDto.getItemContent())
                .itemPrice(itemDto.getItemPrice())
                .itemAddress(itemDto.getItemAddress())
                .itemStatus(itemDto.getItemStatus())
                .dealStatus(itemDto.getDealStatus())
                .dealHow(itemDto.getDealHow())
                .deliveryStatus(itemDto.getDeliveryStatus())
                .itemDeliveryPrice(itemDto.getItemDeliveryPrice())
                .priceStatus(itemDto.getPriceStatus())
                .categoryName(itemDto.getCategoryName())
                .itemCount(itemDto.getItemCount())
                .itemHidden(itemDto.getItemHidden())
                .build();
    }
}
