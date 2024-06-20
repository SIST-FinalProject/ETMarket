package kr.co.sist.etmarket.dto;

import kr.co.sist.etmarket.etenum.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
public class ItemDto {

    private int itemDeliveryPrice;

    private int itemPrice;

    private Long itemId;

    private Timestamp itemResistDate;

    private Timestamp itemUpdateDate;

    private Long userId;

    private String itemAddress;

    private String itemContent;

    private String itemTitle;

    private CategoryName categoryName;

    private DealHow dealHow;

    private DealStatus dealStatus;

    private DeliveryStatus deliveryStatus;

    private ItemHidden itemHidden;

    private ItemStatus itemStatus;

    private PriceStatus priceStatus;

    private int itemCount;

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
}
