package kr.co.sist.etmarket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {


    @GetMapping("/report")
    public String reportUser() {

        return "report/report_user";
    }
}
