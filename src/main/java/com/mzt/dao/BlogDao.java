package com.mzt.dao;

import com.mzt.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/27 15:16
 * @Description : com.mzt.dao
 */
public interface BlogDao extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {


}
