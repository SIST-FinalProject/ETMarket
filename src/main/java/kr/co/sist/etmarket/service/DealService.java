package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.DealDao;
import kr.co.sist.etmarket.entity.Deal;
import kr.co.sist.etmarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealService {

    @Autowired
    private DealDao dealDao;

    // 현재 세션의 유저가 판매자인 경우 판매 내역 조회
    public List<Deal> getSellHistory(User seller) {
        return dealDao.findAllBySeller(seller);
    }

    // 현재 세션의 유저가 구매자인 경우 구매 내역 조회
    public List<Deal> getBuyHistory(User buyer) {
        return dealDao.findAllByBuyer(buyer);
    }

}
