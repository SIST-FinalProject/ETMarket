package kr.co.sist.etmarket.service;


import kr.co.sist.etmarket.dao.ItemDetailRepository;
import kr.co.sist.etmarket.dao.SellerDetailRepository;
import kr.co.sist.etmarket.dto.BasicSellerDto;
import kr.co.sist.etmarket.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final SellerDetailRepository sellerDetailRepository;
    private final ItemDetailRepository itemDetailRepository;

    public BasicSellerDto getUserIdName(Long userId) {

        User user = sellerDetailRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("id값에 해당하는 유저가 없습니다"));

        return new BasicSellerDto(user.getUserId(), user.getUserName());
    }








}
