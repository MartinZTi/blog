package com.mzt.service;

import com.mzt.pojo.User;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/26 11:31
 * @Description : com.mzt.service
 */
public interface UserService {

    User checkUser(String username, String password);

}
