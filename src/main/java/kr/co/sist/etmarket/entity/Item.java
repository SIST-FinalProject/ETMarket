package kr.co.sist.etmarket.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import kr.co.sist.etmarket.etenum.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp itemResistDate;

    @Column(name = "item_update_date")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp itemUpdateDate;

    private String itemTitle;

    private String itemContent;

    private int itemPrice;

    private String itemAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_status")
    private ItemStatus itemStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_deal_status")
    private DealStatus dealStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_deal_how")
    private DealHow dealHow;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_delivery_status")
    private DeliveryStatus deliveryStatus;

    private int itemDeliveryPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_price_status")
    private PriceStatus priceStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_category_name")
    private CategoryName categoryName;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_hidden")
    private ItemHidden itemHidden;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemTag> itemTags = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImg> itemImgs = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCheck> itemChecks = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemLike> itemLikes = new ArrayList<>();
}
