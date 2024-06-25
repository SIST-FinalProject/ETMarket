package kr.co.sist.etmarket.service;

import jakarta.transaction.Transactional;
import kr.co.sist.etmarket.dao.ItemDao;
import kr.co.sist.etmarket.dao.ItemImgDao;
import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.etenum.DealStatus;
import kr.co.sist.etmarket.etenum.ItemHidden;
import kr.co.sist.etmarket.etenum.PriceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final ItemDao itemDao;
    private final UserDao userDao;
    private final ItemImgDao itemImgDao;

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
        itemDto.setDealStatus(DealStatus.판매중);
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

    // Item DB getData
    public ItemDto getDataItem(Long itemId) {
        Item item = itemDao.findByItemId(itemId);

        return covertToDto(item);
    }

    // item 출력을 위해 itemDto로 가공
    private ItemDto covertToDto(Item item) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        ItemDto itemDto = new ItemDto();
        itemDto.setItemId(item.getItemId());
        itemDto.setUserId(item.getUser().getUserId());
        itemDto.setItemTitle(item.getItemTitle());
        itemDto.setItemContent(item.getItemContent());
        itemDto.setItemPriceText(decimalFormat.format(item.getItemPrice()));
        int idx = item.getItemAddress().lastIndexOf("(");
        if (idx != -1) {
            itemDto.setRoadAddress(item.getItemAddress().substring(0, idx).trim());
            itemDto.setDetailAddress(item.getItemAddress().substring(idx + 1, item.getItemAddress().length() - 1).trim());
        } else {
            itemDto.setRoadAddress(item.getItemAddress().trim());
        }
        itemDto.setItemStatus(item.getItemStatus());
        itemDto.setDealHow(item.getDealHow());
        itemDto.setDeliveryStatus(item.getDeliveryStatus());
        itemDto.setItemDeliveryPriceText(decimalFormat.format(item.getItemDeliveryPrice()));
        if (item.getPriceStatus().toString().equals("가능")) {
            itemDto.setPriceStatusCheck(true);
        } else {
            itemDto.setPriceStatusCheck(false);
        }
        itemDto.setCategoryName(item.getCategoryName());
        itemDto.setItemCountText(decimalFormat.format(item.getItemCount()));

        return itemDto;
    }

    // Item DB update
    public Item updateItem(ItemDto itemDto) {
        // 데이터베이스에서 기존 Item 엔티티를 조회합니다.
        Item existingItem = itemDao.findById(itemDto.getItemId()).orElseThrow(() -> new IllegalArgumentException("해당 item ID를 찾을 수 없습니다."));

        // 기존 Item 엔티티를 수정합니다.
        Item updatedItem = processUpdateItemDto(itemDto, existingItem);

        // Item DB Update
        return itemDao.save(updatedItem);
    }

    // itemDto insert를 위해 가공 후 Item Entity로 변환
    public Item processUpdateItemDto(ItemDto itemDto, Item item) {
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

        return item.toBuilder()
                .itemId(itemDto.getItemId())
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
