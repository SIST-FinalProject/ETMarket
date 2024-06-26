package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.ItemUpDto;
import kr.co.sist.etmarket.service.ItemUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemUpController {

    private final ItemUpService itemUpService;

    @Autowired
    public ItemUpController(ItemUpService itemUpService) {
        this.itemUpService = itemUpService;
    }

    @PostMapping("/up")
    public ResponseEntity<String> upItem(@RequestBody ItemUpDto itemUpDto) {
        itemUpService.upItem(itemUpDto);
        return ResponseEntity.ok("Item up successful");
    }
}

