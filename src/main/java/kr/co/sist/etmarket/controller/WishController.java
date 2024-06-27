package kr.co.sist.etmarket.controller;

import kr.co.sist.etmarket.dao.ItemDetailRepository;
import kr.co.sist.etmarket.dao.ItemLikeRepository;
import kr.co.sist.etmarket.dao.SellerDetailRepository;
import kr.co.sist.etmarket.dto.WishRequest;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.ItemLike;
import kr.co.sist.etmarket.entity.User;
import kr.co.sist.etmarket.service.ItemLikeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class WishController {

    private final ItemLikeService itemLikeService;

    @PostMapping("/toggle-wish")
    public ResponseEntity<Map<String, Object>> toggleWish(@RequestBody WishRequest wishRequest) {
        Long itemId = wishRequest.getItemId();
        Long userId = wishRequest.getUserId();

        Map<String, Object> response = itemLikeService.toggleWish(itemId, userId);

        return ResponseEntity.ok(response);
    }

}



