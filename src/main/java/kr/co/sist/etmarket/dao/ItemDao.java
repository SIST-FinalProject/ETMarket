package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.Item;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<Item, Long> {

    // 모든 아이템을 최신 업데이트 순으로 정렬하고 페이징 처리
//    @Query("SELECT i FROM Item i ORDER BY i.itemUpdateDate DESC")
//    Page<Item> findAllOrderByItemUpdateDateDesc(Pageable pageable);

    @Query("SELECT i FROM Item i ORDER BY i.itemUpdateDate DESC")
    Slice<Item> findAllOrderByItemUpdateDateDesc(Pageable pageable);

}
