package kr.co.sist.etmarket.controller;


import jakarta.servlet.http.HttpSession;
import kr.co.sist.etmarket.dto.BasicSellerDto;
import kr.co.sist.etmarket.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;




    @GetMapping("/report/seller")
    public String getReportUserForm(@RequestParam("uid") Long uid, Model model, HttpSession httpSession) {

        BasicSellerDto basicSellerDto = reportService.getUserIdName(uid);
        Long myUid = (Long) httpSession.getAttribute("myUserId");

        if (myUid == null) {
            return "report/error_requireLogin";
        }
        if (myUid.equals(uid)) {
            return "redirect:/";
        }



        model.addAttribute("sellerDto", basicSellerDto);
        return "report/report_user";
    }

    @PostMapping("/submitReportUser")
    public String submitReportUser(@RequestParam("sellerId")Long sellerId,
                                   @RequestParam("reportContent")String reportContent,
                                   HttpSession httpSession) {

        Long myUid = (Long) httpSession.getAttribute("myUserId");

        if (myUid == null) {
            return "redirect:/login";
        }

        reportService.submitReportUser(myUid, sellerId, reportContent);
        return "redirect:/";

    }
}

