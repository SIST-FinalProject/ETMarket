package kr.co.sist.etmarket.dto;

import kr.co.sist.etmarket.entity.Item;

import kr.co.sist.etmarket.etenum.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long itemId;

    private Long userId;

    private String itemTitle;

    private String itemContent;

    private  String itemPriceText;

    private int itemPrice;

    private String roadAddress;

    private String detailAddress;

    private String itemAddress;

    private ItemStatus itemStatus;

    private DealStatus dealStatus;

    private DealHow dealHow;

    private DeliveryStatus deliveryStatus;

    private  String itemDeliveryPriceText;

    private int itemDeliveryPrice;

    private boolean priceStatusCheck;

    private PriceStatus priceStatus;

    private CategoryName categoryName;

    private  String itemCountText;

    private int itemCount;

    private ItemHidden itemHidden;

    private Timestamp itemResistDate;

    private Timestamp itemUpdateDate;

    private Long userSearchId;

    public ItemDto(Integer integer, Integer integer1, long l, Timestamp timestamp, Timestamp timestamp1, long l1, String s, String s1, String s2, CategoryName categoryName, DealHow dealHow, DealStatus dealStatus, DeliveryStatus deliveryStatus, ItemHidden itemHidden, ItemStatus itemStatus, PriceStatus priceStatus, Integer integer2, long l2) {
        itemDeliveryPrice = integer;
        itemPrice = integer1;
        itemId = l;
        itemResistDate = timestamp;
        itemUpdateDate = timestamp1;
        userId = l;
        itemAddress = s;
        itemContent = s1;
        itemTitle = s2;
        this.categoryName = categoryName;
        this.dealHow = dealHow;
        this.dealStatus = dealStatus;
        this.deliveryStatus = deliveryStatus;
        this.itemHidden = itemHidden;
        this.itemStatus = itemStatus;
        this.priceStatus = priceStatus;
        itemCount = integer2;
        userSearchId = l;

    }

    public ItemDto(int itemPrice, long itemId, Timestamp updateDate, long userId, String itemAddress, String itemTitle, DealStatus dealStatus, DeliveryStatus deliveryStatus, ItemHidden itemHidden) {
        this.itemPrice = itemPrice;
        this.itemId = itemId;
        this.itemUpdateDate = updateDate;
        this.userId = userId;
        this.itemAddress = itemAddress;
        this.itemTitle = itemTitle;
        this.dealStatus = dealStatus;
        this.deliveryStatus = deliveryStatus;
        this.itemHidden = itemHidden;
    }

    public ItemDto(Item item) {
        this.itemPrice = item.getItemPrice();
        this.itemId = item.getItemId();
        this.itemUpdateDate = item.getItemUpdateDate();
        this.userId = item.getUser().getUserId();
        this.itemAddress = item.getItemAddress();
        this.itemTitle = item.getItemTitle();
        this.dealStatus = item.getDealStatus();
        this.deliveryStatus = item.getDeliveryStatus();
        this.itemHidden = item.getItemHidden();
    }

}