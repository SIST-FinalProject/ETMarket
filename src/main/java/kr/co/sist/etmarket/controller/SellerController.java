package kr.co.sist.etmarket.controller;

import jakarta.servlet.http.HttpSession;
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

    private final SellerDetailService sellerDetailService;


    @GetMapping("/seller/{sellerId}/items")
    public String sellerItems(@PathVariable("sellerId")Long sellerId, Model model, HttpSession httpSession) {

        SellerDetailDto sellerDetailDto = sellerDetailService.getSellerDetailWithItems(sellerId);
        Long uid = (Long) httpSession.getAttribute("myUserId");

        model.addAttribute("sellerDetailDto", sellerDetailDto);
        model.addAttribute("uid", uid);





        return "seller/seller_detail_item";
    }

    @GetMapping("/seller/{sellerId}/reviews")
    public String sellerReviews(@PathVariable("sellerId") Long sellerId, Model model, HttpSession httpSession) {

        SellerDetailDto sellerDetailDto = sellerDetailService.getSellerDetailWithReviews(sellerId);
        Long uid = (Long) httpSession.getAttribute("myUserId");

        model.addAttribute("sellerDetailDto", sellerDetailDto);
        model.addAttribute("uid", uid);

        return "seller/seller_detail_review";
    }


}
