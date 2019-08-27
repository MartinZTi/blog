package com.mzt.dao;

import com.mzt.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/26 13:38
 * @Description : com.mzt.dao
 */
public interface TypeDao extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
