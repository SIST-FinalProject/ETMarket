package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dto.ItemDto;
import kr.co.sist.etmarket.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deal-status")
public class DealStatusController {

    private final ItemService itemService;

    @PostMapping("/update")
    public ResponseEntity<?> updateDealStatus(@RequestBody ItemDto itemDto) {
        // 아이템 상태 업데이트 로직
        itemService.updateDealStatus(itemDto);

        return ResponseEntity.ok().body(Map.of("success", true, "message", "Status updated successfully"));
    }
}
