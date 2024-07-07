package kr.co.sist.etmarket.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.sist.etmarket.dao.UserDao;
import kr.co.sist.etmarket.dto.UserDto;
import kr.co.sist.etmarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserDto getUserById(Long id) {
        User user = userDao.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return UserDto.fromEntity(user);
    }



    public User getUserName(String userName) {
        return userDao.findByUserName(userName);
    }

}
