package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ItemCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemCheckRepository extends JpaRepository<ItemCheck, Long> {


    @Query("select count(ic) from ItemCheck ic where ic.item.itemId = :itemId")
    int countByItemId(@Param("itemId") long itemId);

}
