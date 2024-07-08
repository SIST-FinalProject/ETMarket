package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ItemImgDao extends JpaRepository<ItemImg, Long> {
    // itemId값에 따른 ItemImg 출력
    List<ItemImg> findByItemItemId(Long itemId);

    // ItemImgId값에 따른 ItemImg 출력
    ItemImg findByItemImgId(Long itemImgId);

    // ItemImgId값에 따른 ItemImg 삭제
    void deleteByItemImgId(Long itemImgId);


    // itemId를 기준으로 가장 작은 itemImgId를 가진 레코드 조회
    Optional<ItemImg> findFirstByItem_ItemIdOrderByItemImgIdAsc(Long itemId);
}

