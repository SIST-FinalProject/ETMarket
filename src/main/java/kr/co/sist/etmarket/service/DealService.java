package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.DealDao;
import kr.co.sist.etmarket.entity.Deal;
import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DealService {

    @Autowired
    private DealDao dealDao;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    // 현재 세션의 유저가 판매자인 경우 판매 내역 조회
    public List<Deal> getSellHistory(User seller) {
        return dealDao.findAllBySellerOrderByDealDateDesc(seller);
    }

    // 현재 세션의 유저가 구매자인 경우 구매 내역 조회
    public List<Deal> getBuyHistory(User buyer) {
        return dealDao.findAllByBuyerOrderByDealDateDesc(buyer);
    }

    public List<User> getChatParticipantsByItemId(Long itemId) {
        return chatRoomService.getChatParticipantNamesByItemId(itemId);
    }

    @Transactional
    public void completeDeal(Long itemId, Long buyerId) {
        Item item = itemService.findById(itemId);
        User buyer = userService.findByUserId(buyerId);
        User seller = userService.findByUserId(chatRoomService.getSellerIdByItemId(itemId));

        // 선택된 구매자 정보를 이용하여 거래내역에 추가
        Deal deal = new Deal();
        deal.setItem(item);
        deal.setItemPrice(item.getItemPrice());
        deal.setBuyer(buyer);
        deal.setSeller(item.getUser());
        deal.setDealDate(Timestamp.valueOf(LocalDateTime.now()));
        deal.setDealMethod("직거래"); //(수정필요)DealMethod값을 어디서 가져와야되는지
        deal.setRatingLeft("N,N"); // 판매자,구매자 평점 남김 여부
        dealDao.save(deal);
    }
    }
