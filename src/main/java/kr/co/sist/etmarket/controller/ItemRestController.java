package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemRestController {

    @Autowired
    private ItemService itemService;

//    @GetMapping("/items")
//    public ResponseEntity<Page<Item>> getAllItems(@RequestParam(defaultValue = "0") int page,
//                                                  @RequestParam(defaultValue = "10") int size) {
//        Page<Item> items = itemService.getAllItems(page, size);
//        return ResponseEntity.ok(items);
//    }
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItems(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        Slice<Item> itemSlice = itemService.getItemSlice(page, size);
        return ResponseEntity.ok()
                .header("X-Has-Next", String.valueOf(itemSlice.hasNext()))
                .body(itemSlice.getContent());
    }


}
