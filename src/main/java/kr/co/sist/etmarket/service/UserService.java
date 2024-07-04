package kr.co.sist.etmarket.service;

import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(Long id) {
        return userDao.findById(id).get();
    }


    /*마이페이지에서 사용*/
    public User findByUserId(Long userId) {
        return userDao.findByUserId(userId);
    }
}
