package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ItemTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTagDao extends JpaRepository<ItemTag, Long> {
}
