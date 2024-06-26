package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ItemLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemLikeDao extends JpaRepository<ItemLike, Long> {

    @Query("SELECT il.item.itemId, COUNT(il.itemLikeId) AS likeCount FROM ItemLike il GROUP BY il.item.itemId")
    List<Object[]> countLikesByItemId();
}
