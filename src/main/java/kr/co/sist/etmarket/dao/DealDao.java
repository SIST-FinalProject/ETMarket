package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.Deal;
import kr.co.sist.etmarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealDao extends JpaRepository<Deal, Long> {
    /* 판매자 ID로 판매 내역 조회 */
    List<Deal> findAllBySeller(User sellerId);
    /* 구매자 ID로 구매 내역 조회 */
    List<Deal> findAllByBuyer(User buyerId);
}

