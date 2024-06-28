package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.entity.ItemTag;
import kr.co.sist.etmarket.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserSearchController {

    @Autowired
    private UserSearchService userSearchService;


    @GetMapping("/search")
    public String findItemsByContentAndItemTitle(@RequestParam String content,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 Model model) {
        Page<ItemDto> itemDtos = userSearchService.getItemTitle(content, page, size);

        int totalPages = itemDtos.getTotalPages(); // 총 페이지 수
        int currentPage = page; // 현재 페이지 (0부터 시작)
        int maxDisplayedPages = 5; // 한 번에 표시할 최대 페이지 번호 수

        // 시작 페이지 계산: 현재 페이지에서 표시할 페이지 수의 절반을 뺀 값, 최소 0
        int startPage = Math.max(0, currentPage - maxDisplayedPages / 2);
        // 끝 페이지 계산: 시작 페이지 + 표시할 최대 페이지 수 - 1, 최대 총 페이지 - 1
        int endPage = Math.min(totalPages - 1, startPage + maxDisplayedPages - 1);

        // 끝 페이지와 시작 페이지 사이의 간격이 최대 표시 페이지 수보다 작으면
        if (endPage - startPage < maxDisplayedPages - 1) {
            // 시작 페이지 재조정: 끝 페이지 - 최대 표시 페이지 수 + 1, 최소 0
            startPage = Math.max(0, endPage - maxDisplayedPages + 1);
        }

        // 모델에 필요한 속성 추가
        model.addAttribute("content", content); // 검색된 내용
        model.addAttribute("items", itemDtos); // 검색된 아이템들
        model.addAttribute("currentPage", currentPage); // 현재 페이지
        model.addAttribute("totalPages", totalPages); // 총 페이지 수
        model.addAttribute("startPage", startPage); // 표시할 시작 페이지
        model.addAttribute("endPage", endPage); // 표시할 끝 페이지
        model.addAttribute("size", size); // 페이지당 아이템 수

        return "search/search";
    }

}
