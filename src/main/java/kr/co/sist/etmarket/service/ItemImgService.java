package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemImgDao;
import kr.co.sist.etmarket.entity.ItemImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemImgService {

    private final ItemImgDao itemImgDao;

    @Autowired
    public ItemImgService(ItemImgDao itemImgDao) {
        this.itemImgDao = itemImgDao;
    }

    public ItemImg getFirstImageByItemId(Long itemId) {
        Optional<ItemImg> optionalItemImg = itemImgDao.findFirstByItem_ItemIdOrderByItemImgIdAsc(itemId);
        return optionalItemImg.orElse(null); // orElse를 사용하여 값이 없을 경우 null을 반환하도록 처리
    }
}
