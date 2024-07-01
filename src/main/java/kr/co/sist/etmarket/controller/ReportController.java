package kr.co.sist.etmarket.controller;


import kr.co.sist.etmarket.dto.BasicSellerDto;
import kr.co.sist.etmarket.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;




    @GetMapping("/report/seller")
    public String reportUser(@RequestParam("uid") Long uid, Model model) {

        BasicSellerDto basicSellerDto = reportService.getUserIdName(uid);

        model.addAttribute("sellerDto", basicSellerDto);
        return "report/report_user";
    }
}
