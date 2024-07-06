package kr.co.sist.etmarket.controller;

import jakarta.servlet.http.HttpSession;
import kr.co.sist.etmarket.entity.Deal;
import kr.co.sist.etmarket.entity.ItemImg;
import kr.co.sist.etmarket.service.DealService;
import kr.co.sist.etmarket.service.ItemImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/deal/history")
@RequiredArgsConstructor
public class DealHistoryController {

    private final DealService dealService;
    private final ItemImgService itemImgService;

    @GetMapping
    public ModelAndView dealHistoryPage() {
        return new ModelAndView("myPage/dealHistory");
    }

    @GetMapping("/{type}")
    public ResponseEntity<String> getHistoryByType(HttpSession session, @PathVariable String type) {
        try {
            Long userId = (Long) session.getAttribute("myUserId");

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션에서 userId를 찾을 수 없습니다.");
            }

            List<Deal> history;
            if ("sell".equals(type)) {
                history = dealService.getSellHistory(userId);
            } else if ("buy".equals(type)) {
                history = dealService.getBuyHistory(userId);
            } else {
                return ResponseEntity.badRequest().body("올바르지 않은 타입입니다.");
            }

            // HTML 문자열 생성
            String html = generateHtml(history,userId);

            return ResponseEntity.ok(html);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("거래 내역을 조회하는 도중 오류가 발생하였습니다. " + e.getMessage());
        }
    }

    private String generateHtml(List<Deal> history, Long userId) {
        StringBuilder sb = new StringBuilder();

        for (Deal deal : history) {
            sb.append("<article class=\"sc-cd837f27-0 cpZssi\">");
            sb.append("<header class=\"eOxcte\">");
            sb.append("<h3>").append(deal.getDealDate()).append("</h3>");
            sb.append("</header>");
            sb.append("<hr class=\"sc-kAyceB cdAyDo bun-ui-divider\">");
            sb.append("<section class=\"sc-cd837f27-2 gyvxQm\">");
            sb.append("<section class=\"sc-cd837f27-7 DJdJS\">");

            // 이미지 처리
            sb.append("<div>");
            if (deal.getItem() != null && deal.getItem().getItemId() != null) {
                ItemImg itemImg = itemImgService.getFirstImageByItemId(deal.getItem().getItemId());
                if (itemImg != null && itemImg.getItemImg() != null) {
                    String itemImgUrl = itemImg.getItemImg(); // 이미지의 URL을 가져옵니다.
                    sb.append("<img src=\"").append(itemImgUrl).append("\" alt=\"상품 사진\" loading=\"lazy\">");
                } else {
                    sb.append("<i class=\"bi bi-card-image\"></i>");
                }
            } else {
                sb.append("<i class=\"bi bi-card-image\"></i>");
            }
            sb.append("</div>");


            sb.append("<section>");
            sb.append("<div class=\"title-container\">");
            sb.append("<header class=\"sc-cd837f27-4 hmyVuO\">").append(deal.getItem().getItemTitle()).append("</header>");
            sb.append("</div>");
            sb.append("<div class=\"item-info\">");
            sb.append("<p class=\"item-price\">").append(NumberFormat.getInstance().format(deal.getItemPrice())).append("원</p>");
            sb.append("</div>");
            sb.append("<div class=\"sc-cd837f27-5 bxbsbo\">");
            sb.append("<p class=\"sc-cd837f27-5 bxb\">").append(deal.getBuyer().getUserName()).append(" / ").append(deal.getDealMethod()).append("</p>");
            sb.append("</div>");
            sb.append("</section>");
            sb.append("</section>");

            sb.append("<footer class=\"sc-cd837f27-3 bjPSeA transaction-card-footer\">");
            sb.append("<input type=\"hidden\" name=\"dealId\" value=\"").append(deal.getDealId()).append("\">");
            sb.append("<input type=\"hidden\" name=\"userId\" value=\"").append(userId).append("\">");
            sb.append("<button class=\"fbpoRr leaveRatingButton\" color=\"secondaryRed\">후기 남기기</button>");
            sb.append("</footer>");

            sb.append("</article>");
        }

        return sb.toString();
    }
}





