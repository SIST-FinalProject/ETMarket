package kr.co.sist.etmarket.dto;

import kr.co.sist.etmarket.etenum.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
