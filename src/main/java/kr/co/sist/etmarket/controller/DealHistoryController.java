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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/deal/history")
public class DealHistoryController {

    @Autowired
    private UserService userService;
    private DealService dealService;
    private final ItemService itemService;
    private final ItemImgService itemImgService;

    @GetMapping("/dealHistory")
    public String getDealHistory(HttpSession session, Model model) {
        try {
            Long userId = (Long) session.getAttribute("userId");

            User seller = userService.getUserById(userId);
            List<Deal> salesHistory = dealService.getSellHistory(seller);

            User buyer = userService.getUserById(userId);
            List<Deal> purchasesHistory = dealService.getBuyHistory(buyer);

            // 각 거래에 대한 아이템 이미지 정보 가져오기
            List<ItemImgDto> salesItemImgList = salesHistory.stream()
                    .map(deal -> itemImgService.getFirstImageByItemId(deal.getItem().getItemId()))
                    .filter(Objects::nonNull)
                    .map(itemImg -> new ItemImgDto(itemImg.getItemImgId(), itemImg.getItemImg()))
                    .collect(Collectors.toList());

            List<ItemImgDto> purchasesItemImgList = purchasesHistory.stream()
                    .map(deal -> itemImgService.getFirstImageByItemId(deal.getItem().getItemId()))
                    .filter(Objects::nonNull)
                    .map(itemImg -> new ItemImgDto(itemImg.getItemImgId(), itemImg.getItemImg()))
                    .collect(Collectors.toList());

            model.addAttribute("salesHistory", salesHistory);
            model.addAttribute("purchasesHistory", purchasesHistory);
            model.addAttribute("salesItemImgList", salesItemImgList);
            model.addAttribute("purchasesItemImgList", purchasesItemImgList);

            return "dealHistory"; // dealHistory.html 뷰로 이동
        } catch (Exception e) {
            return "errorPage"; // 예외 발생 시 errorPage.html 뷰로 이동
        }
    }
}
