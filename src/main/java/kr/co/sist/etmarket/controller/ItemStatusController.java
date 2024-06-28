package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item-status")
public class ItemStatusController {

    private final ItemService itemService;

    @PostMapping("/update")
    public ResponseEntity<?> updateItemStatus(@RequestBody ItemDto itemDto) {
        // 아이템 상태 업데이트 로직
        itemService.updateItemStatus(itemDto);

        return ResponseEntity.ok().body(Map.of("success", true, "message", "Status updated successfully"));
    }
}
