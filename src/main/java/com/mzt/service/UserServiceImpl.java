package com.mzt.service;

import com.mzt.dao.UserDao;
import com.mzt.pojo.User;
import com.mzt.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/26 11:33
 * @Description : com.mzt.service
 */
@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));

        return user;
    }

}
