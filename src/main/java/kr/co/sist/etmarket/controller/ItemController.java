package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.dto.ItemTagDto;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.service.ItemImgService;
import kr.co.sist.etmarket.service.ItemService;
import kr.co.sist.etmarket.service.ItemTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemTagService itemTagService;
    private final ItemImgService itemImgService;

    @GetMapping("/item/insertForm")
    public String insertForm(){
        return "item/itemInsertForm";
    }

    @PostMapping("/item/insert")
    public String insert(@ModelAttribute ItemDto itemDto,
                         @ModelAttribute ItemTagDto itemTagDto,
                         @RequestParam ArrayList<MultipartFile> itemImgUpload) {
        Item item = itemService.insertItem(itemDto);

        if (!itemTagDto.getItemTags().isBlank()) {
            itemTagService.insertItemTag(itemTagDto, item);
        }

        itemImgService.insertItemImg(itemImgUpload, item);

        return "redirect:insertForm";
    }
}
