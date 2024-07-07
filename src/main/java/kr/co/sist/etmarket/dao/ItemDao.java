package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.etenum.CategoryName;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<Item, Long> {

    // 모든 아이템을 최신 업데이트 순으로 정렬하고 페이징 처리
//    @Query("SELECT i FROM Item i ORDER BY i.itemUpdateDate DESC")
//    Page<Item> findAllOrderByItemUpdateDateDesc(Pageable pageable);

    @Query("SELECT i FROM Item i ORDER BY i.itemUpdateDate DESC") // join을 하지 않고 item 엔티티를 직접 조회하기에 연관된 다른 엔티티들도 같이 조회 가능
    Slice<Item> findAllOrderByItemUpdateDateDesc(Pageable pageable);
  
  // itemId값에 따른 getData
    Item findByItemId(Long itemId);

    @Query("SELECT i FROM Item i " +
            "WHERE i.categoryName = :category " +
            "ORDER BY i.itemUpdateDate DESC")
    Page<Item> findItemsByCategoryName(@Param("category") CategoryName category, Pageable pageable);


    // itemId값에 따른 delete
    void deleteByItemId(Long itemId);

    Item findItemByItemId(Long itemId);

    @Query("SELECT i.user.userName FROM Item i WHERE i.itemId = :itemId")
    String findUserNameByItemId(@Param("itemId") Long itemId);

}
