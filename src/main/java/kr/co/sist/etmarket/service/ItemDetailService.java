package kr.co.sist.etmarket.service;


import kr.co.sist.etmarket.dao.ItemCheckRepository;
import kr.co.sist.etmarket.dao.ItemDetailRepository;
import kr.co.sist.etmarket.dao.ItemLikeRepository;
import kr.co.sist.etmarket.dto.DetailTagDto;
import kr.co.sist.etmarket.dto.ItemDetailDto;
import kr.co.sist.etmarket.dto.ItemImgDto;
import kr.co.sist.etmarket.dto.SimilarItemDto;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.ItemImg;
import kr.co.sist.etmarket.entity.ItemTag;
import kr.co.sist.etmarket.etenum.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemDetailService {

    private final ItemDetailRepository itemDetailRepository;
    private final ItemLikeRepository itemLikeRepository;
    private final ItemCheckRepository itemCheckRepository;

    @Autowired
    public ItemDetailService(ItemDetailRepository itemDetailRepository, ItemLikeRepository itemLikeRepository,
                             ItemCheckRepository itemCheckRepository) {
        this.itemDetailRepository = itemDetailRepository;
        this.itemLikeRepository = itemLikeRepository;
        this.itemCheckRepository = itemCheckRepository;
    }

    // 상품 상세정보
    public ItemDetailDto getItemDetail(Long itemId) {

        Item item = itemDetailRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException("id값에 해당하는 상품이 없습니다"));

        ItemDetailDto itemDetailDto = new ItemDetailDto();
        DecimalFormat formatter = new DecimalFormat("#,###");

        itemDetailDto.setItemId(item.getItemId());
        itemDetailDto.setCategoryName(item.getCategoryName());
        itemDetailDto.setItemTitle(item.getItemTitle());
        itemDetailDto.setItemContent(item.getItemContent());
        itemDetailDto.setItemPrice(formatter.format(item.getItemPrice()));
        itemDetailDto.setWishCount(itemLikeRepository.countByItemId(item.getItemId()));
        itemDetailDto.setViewCount(itemCheckRepository.countByItemId(item.getItemId()));
        itemDetailDto.setItemStatus(item.getItemStatus());
        itemDetailDto.setDealStatus(item.getDealStatus());
        itemDetailDto.setItemHidden(item.getItemHidden());
        itemDetailDto.setDealHow(item.getDealHow());
        itemDetailDto.setItemAddress(item.getItemAddress());
        itemDetailDto.setDeliveryStatus(item.getDeliveryStatus());
        itemDetailDto.setItemDeliveryPrice(item.getItemDeliveryPrice());
        itemDetailDto.setItemResistDate(item.getItemResistDate());
        itemDetailDto.setItemUpdateDate(item.getItemUpdateDate());
        itemDetailDto.setDetailTagDtoList(item.getItemTags().stream()
                .map(this::convertTagToDto)
                .collect(Collectors.toList()));
        itemDetailDto.setItemImgDtoList(item.getItemImgs().stream()
                .map(this::convertImageToDto)
                .collect(Collectors.toList()));

        return itemDetailDto;

    }

    // 비슷한 상품 2개
    public List<SimilarItemDto> getSimilarItems(Long itemId, CategoryName categoryName) {

        List<SimilarItemDto> similarItemDtoList = new ArrayList<>();
        DecimalFormat formatter = new DecimalFormat("#,###");


        Pageable pageable = PageRequest.of(0, 2);
        List<Item> itemsByCategory = itemDetailRepository.findRandomItemsByCategory(categoryName, itemId,pageable);

        // 상품이 2개 미만인 경우 추가로 조회하여 채움
        if (itemsByCategory.size() < 2) {
            List<Item> additionalItems = itemDetailRepository.findRandomItems(itemId, PageRequest.of(0, 2 - itemsByCategory.size()));
            itemsByCategory.addAll(additionalItems);
        }
        for (Item item : itemsByCategory) {
            SimilarItemDto similarItemDto = new SimilarItemDto();

            similarItemDto.setItemId(item.getItemId());
            similarItemDto.setItemTitle(item.getItemTitle());
            similarItemDto.setItemPrice(formatter.format(item.getItemPrice()));
            similarItemDto.setImgUrl(item.getItemImgs().get(0).getItemImg());
            similarItemDto.setSellerId(item.getUser().getUserId());
            similarItemDto.setSellerName(item.getUser().getUserName());

            similarItemDtoList.add(similarItemDto);
        }

        return similarItemDtoList;
    }

    private String calculateTimeAgo(LocalDateTime resistTime, LocalDateTime now) {
        Duration duration = Duration.between(resistTime, now);

        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long weeks = days / 7;
        long months = days / 30;
        long years = days / 365;

        if (seconds < 60) {
            return seconds + "초 전";
        } else if (minutes < 60) {
            return minutes + "분 전";
        } else if (hours < 24) {
            return hours + "시간 전";
        } else if (days < 7) {
            return days + "일 전";
        } else if (weeks < 4) {
            return weeks + "주 전";
        } else if (months < 12) {
            return months + "달 전";
        } else {
            return years + "년 전";
        }
    }

    private DetailTagDto convertTagToDto(ItemTag itemTag) {

        return new DetailTagDto(itemTag.getItemTag());
    }

    private ItemImgDto convertImageToDto(ItemImg itemImg) {

        return new ItemImgDto(itemImg.getItemImgId(), itemImg.getItemImg(), itemImg.getResistDate(), itemImg.getUpdateDate());
    }

    public String convertTime(Timestamp timestamp) {

        LocalDateTime inputTime = timestamp.toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();

        return calculateTimeAgo(inputTime, now);
    }
}
