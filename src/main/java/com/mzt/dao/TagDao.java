package com.mzt.dao;

import com.mzt.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/27 12:28
 * @Description : com.mzt.dao
 */
public interface TagDao extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

}
