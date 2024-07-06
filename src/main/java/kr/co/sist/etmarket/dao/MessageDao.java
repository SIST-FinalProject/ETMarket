package kr.co.sist.etmarket.dao;

import kr.co.sist.etmarket.entity.ChatRoom;
import kr.co.sist.etmarket.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<Message, Long> {



}
