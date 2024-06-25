package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.dto.ItemImgDto;
import kr.co.sist.etmarket.dto.ItemTagDto;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.service.ItemImgService;
import kr.co.sist.etmarket.service.ItemService;
import kr.co.sist.etmarket.service.ItemTagService;
import kr.co.sist.etmarket.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemTagService itemTagService;
    private final ItemImgService itemImgService;

    @GetMapping("/item/insertForm")
    public String insertForm(@RequestParam Long userId, Model model){
        model.addAttribute("userId", userId);

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

        return "redirect:/";
    }

    @GetMapping("/item/updateForm")
    public String updateForm(@RequestParam Long itemId, Model model) {
        ItemDto itemDto = itemService.getDataItem(itemId);
        List<ItemImgDto> itemImgDtos = itemImgService.getItemImgDataByItemId(itemId);
        String itemTags = itemTagService.getItemTagsByItemId(itemId);

        model.addAttribute("itemDto", itemDto);
        model.addAttribute("itemImgDtos", itemImgDtos);
        model.addAttribute("itemImgCount", itemImgDtos.size());
        model.addAttribute("itemTags", itemTags);

        return "item/itemUpdateForm";
    }

    @PostMapping("/item/update")
    public String update(@ModelAttribute ItemDto itemDto,
                         @ModelAttribute ItemTagDto itemTagDto,
                         @ModelAttribute ItemImgDto itemImgDto,
                         @RequestParam ArrayList<MultipartFile> itemImgUpload) {
        Item item = itemService.updateItem(itemDto);

        if (!itemTagDto.getItemTags().isBlank()) {
            itemTagService.deleteItemTag(itemDto.getItemId());

            itemTagService.insertItemTag(itemTagDto, item);
        }

        int itemImgCount = itemImgService.getItemImgDataByItemId(itemDto.getItemId()).size();
        itemImgService.updateItemImg(itemImgUpload, itemImgDto, itemImgCount, item);

        return "redirect:/";
    }
}
