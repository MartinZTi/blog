package com.mzt.dao;

import com.mzt.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/26 11:34
 * @Description : com.mzt.dao
 */

public interface UserDao extends JpaRepository<User,Long> {
    //long, 主键
    User findByUsernameAndPassword(String username, String password);


}
