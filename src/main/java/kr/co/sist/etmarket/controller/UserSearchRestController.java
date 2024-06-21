package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.dto.UserSearchDto;
import kr.co.sist.etmarket.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class UserSearchRestController {

    @Autowired
    private UserSearchService userSearchService;

    @PostMapping("/search/insert")
    public void insertContent(@RequestBody UserSearchDto userSearchDto) {
        System.out.println("userSearchDto = " + userSearchDto);
        userSearchService.insertContent(userSearchDto);
    }

    @GetMapping("/search/items")
    public List<ItemDto> findItemsByContentAndItemTitle(@RequestParam String content) {
        return userSearchService.getItemTitle(content);
    }


    @PostMapping("/search/delete")
    public void deleteContent(@RequestBody UserSearchDto userSearchDto) {
        System.out.println("userSearchDto = " + userSearchDto);
        userSearchService.deleteContent(userSearchDto.getContent());
    }

    @PostMapping("/search/init")
    public List<UserSearchDto> getTop8SearchContent(@RequestParam Long userId) {
        try {
            UserSearchDto userSearchDto = new UserSearchDto();
            userSearchDto.setUserId(userId);
            return userSearchService.getTop8SearchContent(userSearchDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 예외를 다시 던져 클라이언트에게 전달
        }
    }
//
//    @GetMapping("/items")
//    public String findItemsByContentAndItemTitle(@RequestParam String content, Model model) {
////        return userSearchService.getItemTitle(content);
////        ModelAndView modelAndView = new ModelAndView();
//        List<ItemDto> itemDtos = userSearchService.getItemTitle(content);
//        model.addAttribute("items", itemDtos);
////        modelAndView.addObject("items", itemDtos);
////        modelAndView.setViewName("list/list");
//
//        return "list/list";
//    }

//    // 인기 검색어 8개 출력
//    @GetMapping("/popular")
//    public List<UserSearchDto> getTop8PopularContent() {
//        return userSearchService.getTop8PopularContent();
//    }

//    // 최근 검색어를 클릭 시 상단으로 이동시키기 위함
//    @PostMapping("/update")
//    public void getUserSearchUpdate(@RequestBody UserSearchDto userSearchDto) {
//        User user = new User();
//        user.setId(userSearchDto.getUserId());
//        userSearchService.getUserSearchUpdate(user, userSearchDto.getContent());
//    }



}
