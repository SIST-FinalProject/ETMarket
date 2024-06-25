package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ItemLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemLikeRepository extends JpaRepository<ItemLike, Long> {

    @Query("select count(il) from ItemLike il where il.item.itemId = :itemId ")
    int countByItemId(@Param("itemId")Long itemId);
}

