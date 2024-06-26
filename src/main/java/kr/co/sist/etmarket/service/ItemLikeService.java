package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemLikeDao;
import kr.co.sist.etmarket.dto.ItemLikeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemLikeService {

    private final ItemLikeDao itemLikeDao;

    public ItemLikeService(ItemLikeDao itemLikeDao) {
        this.itemLikeDao = itemLikeDao;
    }

    public List<ItemLikeDto> countLikesByItemId() {
        List<Object[]> result = itemLikeDao.countLikesByItemId();
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
