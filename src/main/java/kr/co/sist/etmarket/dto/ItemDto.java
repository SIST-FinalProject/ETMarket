package kr.co.sist.etmarket.dto;

import java.sql.Timestamp;

import kr.co.sist.etmarket.etenum.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long itemId;
    private Long userId; // User 엔티티의 외래 키
    private Timestamp itemResistDate;
    private Timestamp itemUpdateDate;
    private String itemTitle;
    private String itemContent;
    private int itemPrice;
    private String itemAddress;

    // 추가 필드들
    private ItemStatus itemStatus;
    private DealStatus dealStatus;
    private DealHow dealHow;
    private DeliveryStatus deliveryStatus;
    private int itemDeliveryPrice;
    private PriceStatus priceStatus;
    private CategoryName categoryName;
    private ItemHidden itemHidden;


}
