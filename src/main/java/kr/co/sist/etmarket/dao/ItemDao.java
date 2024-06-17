package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<Item, Long> {
}
