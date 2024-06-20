package kr.co.sist.etmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemTempController {

    @GetMapping("/detail")
    public String itemDetail() {
        return "item/item_detail";
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
