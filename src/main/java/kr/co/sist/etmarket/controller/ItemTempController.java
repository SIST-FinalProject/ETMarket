package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.KeyValueMapper;
import kr.co.sist.etmarket.dto.ItemDetailDto;
import kr.co.sist.etmarket.dto.SimilarItemDto;
import kr.co.sist.etmarket.service.ItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ItemTempController {

    private final ItemDetailService itemDetailService;

    @Autowired
    public ItemTempController(ItemDetailService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }

    @GetMapping("/items/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model) {

        ItemDetailDto itemDetailDto = itemDetailService.getItemDetail(itemId);
        String updateTime = itemDetailService.convertTime(itemDetailDto.getItemUpdateDate());
        String categoryName = KeyValueMapper.getValue(itemDetailDto.getCategoryName());
        String itemStatus = KeyValueMapper.getValue(itemDetailDto.getItemStatus());
        List<SimilarItemDto> similarItemDtoList = itemDetailService.getSimilarItems(itemDetailDto.getItemId(),itemDetailDto.getCategoryName());

        model.addAttribute("itemDetailDto", itemDetailDto);
        model.addAttribute("updateTime", updateTime);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("itemStatus", itemStatus);
        model.addAttribute("similarItemList", similarItemDtoList);


        return "item/item_detail";
    }


    @GetMapping("/detail")
    public String itemDetailTest() {

        return "item/item_detail_backup";
    }

    @GetMapping("/seller/item")
    public String sellerItemList() {
        return "seller/seller_detail_item.html";
    }
    @GetMapping("/seller/review")
    public String sellerReviewList() {
        return "seller/seller_detail_review.html";
    }
}
