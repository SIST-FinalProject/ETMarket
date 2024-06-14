package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.ItemDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemDao itemDao;
}
