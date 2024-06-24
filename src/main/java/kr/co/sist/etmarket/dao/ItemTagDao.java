package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ItemTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemTagDao extends JpaRepository<ItemTag, Long> {
    List<ItemTag> findByItemItemId(Long itemId);

    void deleteByItemItemId(Long itemId);
}
