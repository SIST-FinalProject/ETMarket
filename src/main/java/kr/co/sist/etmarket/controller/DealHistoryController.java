package kr.co.sist.etmarket.controller;

import jakarta.servlet.http.HttpSession;
import kr.co.sist.etmarket.dto.ItemImgDto;
import kr.co.sist.etmarket.entity.Deal;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.service.DealService;
import kr.co.sist.etmarket.service.ItemImgService;
import kr.co.sist.etmarket.service.ItemService;
import kr.co.sist.etmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class DealHistoryController {

    @Autowired
    private UserService userService;
    private final DealService dealService;
    private final ItemService itemService;
    private final ItemImgService itemImgService;

    @GetMapping("/deal/history")
    public String getDealHistory(HttpSession session, Model model) {
        try {
            Long userId = (Long) session.getAttribute("myUserId");

            if (userId == null) {
                throw new IllegalArgumentException("세션에서 userId를 찾을 수 없습니다.");
            }

            User seller = userService.getUserById(userId);
            List<Deal> saleHistory = dealService.getSellHistory(seller);

            User buyer = userService.getUserById(userId);
            List<Deal> buyHistory = dealService.getBuyHistory(buyer);

            // 각 거래에 대한 아이템 이미지 정보 가져오기
            List<ItemImgDto> saleItemImgList = saleHistory.stream()
                    .map(deal -> itemImgService.getFirstImageByItemId(deal.getItem().getItemId()))
                    .filter(Objects::nonNull)
                    .map(itemImg -> new ItemImgDto(itemImg.getItemImgId(), itemImg.getItemImg()))
                    .collect(Collectors.toList());

            List<ItemImgDto> buyItemImgList = buyHistory.stream()
                    .map(deal -> itemImgService.getFirstImageByItemId(deal.getItem().getItemId()))
                    .filter(Objects::nonNull)
                    .map(itemImg -> new ItemImgDto(itemImg.getItemImgId(), itemImg.getItemImg()))
                    .collect(Collectors.toList());

            model.addAttribute("saleHistory", saleHistory);
            model.addAttribute("buyHistory", buyHistory);
            model.addAttribute("saleItemImgList", saleItemImgList);
            model.addAttribute("buyItemImgList", buyItemImgList);

            return "myPage/history";
        } catch (Exception e) {
            throw new RuntimeException("거래 내역을 조회하는 도중 오류가 발생하였습니다.", e);
        }
    }
}