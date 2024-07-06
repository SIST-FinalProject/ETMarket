package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.MessageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private static MessageDao messageDao;

}
