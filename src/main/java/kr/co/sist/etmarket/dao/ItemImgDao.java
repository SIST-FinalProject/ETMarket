package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemImgDao extends JpaRepository<ItemImg, Long> {

    // itemId를 기준으로 가장 작은 itemImgId를 가진 레코드 조회
    Optional<ItemImg> findFirstByItem_ItemIdOrderByItemImgIdAsc(Long itemId);
}

