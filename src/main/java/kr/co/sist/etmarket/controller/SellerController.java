package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.SellerDetailDto;
import kr.co.sist.etmarket.service.ItemDetailService;
import kr.co.sist.etmarket.service.SellerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class SellerController {

    private final ItemDetailService itemDetailService;
    private final SellerDetailService sellerDetailService;




    @GetMapping("/seller/{sellerId}/items")
    public String sellerItems(@PathVariable Long sellerId, Model model) {

        SellerDetailDto sellerDetailDto = sellerDetailService.getSellerDetailWithItems(sellerId);

        model.addAttribute("sellerDetailDto", sellerDetailDto);


        return "seller/seller_detail_item";
    }

    @GetMapping("/seller/{sellerId}/reviews")
    public String sellerReviews(@PathVariable Long sellerId, Model model) {

        return "seller/seller_detail_review";
    }


}
