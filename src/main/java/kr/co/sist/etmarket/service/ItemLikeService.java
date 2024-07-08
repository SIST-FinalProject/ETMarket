package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemDetailRepository;
import kr.co.sist.etmarket.dao.ItemLikeRepository;
import kr.co.sist.etmarket.dao.SellerDetailRepository;
import kr.co.sist.etmarket.dto.ItemLikeDto;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.ItemLike;
import kr.co.sist.etmarket.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemLikeService {

    private final SellerDetailRepository sellerDetailRepository;
    private final ItemDetailRepository itemDetailRepository;
    private final ItemLikeRepository itemLikeRepository;

    public boolean isWishItem(Long userId, Long itemId) {

        Optional<ItemLike> itemLike = itemLikeRepository.findByItem_ItemIdAndUser_UserId(itemId, userId);

        if (itemLike.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Object> toggleWish(Long itemId, Long userId) {
        Optional<ItemLike> isWishItem = itemLikeRepository.findByItem_ItemIdAndUser_UserId(itemId, userId);

        Map<String, Object> response = new HashMap<>();
        if (isWishItem.isPresent()) {
            itemLikeRepository.deleteByItem_ItemIdAndUser_UserId(itemId, userId);
            response.put("isWished", false);
        } else {
            ItemLike itemLike = new ItemLike();
            User user = sellerDetailRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("id에 맞는 유저가 없습니다"));
            Item item = itemDetailRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException("id에 맞는 상품이 없습니다"));

            itemLike.setItem(item);
            itemLike.setUser(user);
            itemLikeRepository.save(itemLike);

            response.put("isWished", true);
        }

        int wishCount = itemLikeRepository.countByItemId(itemId);
        response.put("wishCount", wishCount);

        return response;
    }


    /*마이페이지에서 사용*/
    public List<ItemLikeDto> countLikesByItemId() {
        List<Object[]> result = itemLikeRepository.countLikesByItemId();
        List<ItemLikeDto> dtos = new ArrayList<>();

        for (Object[] row : result) {
            ItemLikeDto dto = new ItemLikeDto();
            dto.setItemId((Long) row[0]);
            dto.setLikeCount((Long) row[1]);
            dtos.add(dto);
        }

        return dtos;
    }

}