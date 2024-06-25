package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.Item;
import kr.co.sist.etmarket.etenum.CategoryName;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemDetailRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.categoryName = :categoryName and i.itemId <> :itemId order by rand()")
    List<Item> findRandomItemsByCategory(@Param("categoryName") CategoryName categoryName, @Param("itemId") Long itemId, Pageable pageable);

    @Query("select i from Item i where i.itemId <> :itemId order by rand()")
    List<Item> findRandomItems(@Param("itemId") Long itemId, Pageable pageable);

}
