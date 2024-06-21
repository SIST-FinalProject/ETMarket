package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemTagDao;
import kr.co.sist.etmarket.dto.ItemTagDto;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.ItemTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemTagService {
    private final ItemTagDao itemTagDao;

    //ItemTag DB insert
    public void insertItemTag(ItemTagDto itemTagDto, Item item) {
        // 태그 분리
        List<String> itemTagList = Arrays.asList(itemTagDto.getItemTags().split("\\s+"));

        for(String tag:itemTagList) {
            ItemTag itemTag = ItemTag.builder()
                    .item(item)
                    .itemTag(tag)
                    .build();

            itemTagDao.save(itemTag);
        }
    }
}
