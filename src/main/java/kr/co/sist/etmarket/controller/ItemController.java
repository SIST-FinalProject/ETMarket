package kr.co.sist.etmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    @GetMapping("/item/insertForm")
    public String insertForm(){
        return "item/itemInsertForm";
    }
}
