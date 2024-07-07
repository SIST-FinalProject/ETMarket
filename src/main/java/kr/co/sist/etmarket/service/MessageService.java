package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.MessageDao;
import kr.co.sist.etmarket.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageDao messageDao;

    public void save(Message message) {
        messageDao.save(message);
    }

}
