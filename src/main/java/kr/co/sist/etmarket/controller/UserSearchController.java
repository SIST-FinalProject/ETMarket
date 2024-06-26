package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserSearchController {

    @Autowired
    private UserSearchService userSearchService;


//    @GetMapping("search/items")
//    public String findItemsByContentAndItemTitle(@RequestParam String content, Model model) {
////        List<ItemDto> itemDtos = userSearchService.getItemTitle(content);
////        model.addAttribute("items", itemDtos);
//
//        return "main/main";
//    }

}
