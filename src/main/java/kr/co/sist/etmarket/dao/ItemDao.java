package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.dto.ItemDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<ItemDto, Long> {
}
