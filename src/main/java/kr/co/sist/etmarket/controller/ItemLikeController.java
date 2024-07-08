package kr.co.sist.etmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemLikeController {
    @GetMapping("/item/like")
    public String itemLike(){
        return "myPage/itemLike";
    }
}
