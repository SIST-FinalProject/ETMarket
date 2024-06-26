package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemDao;
import kr.co.sist.etmarket.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    public Slice<Item> getItemSlice(Pageable pageable) {
        return itemDao.findAllOrderByItemUpdateDateDesc(pageable);
    }
//    public Slice<Item> getItemSlice(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return itemDao.findAllOrderByItemUpdateDateDesc(pageable);
//    }

}
