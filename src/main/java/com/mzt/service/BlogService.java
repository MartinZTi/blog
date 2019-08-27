package com.mzt.service;

import com.mzt.pojo.Blog;
import com.mzt.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/27 15:12
 * @Description : com.mzt.service
 */
public interface BlogService {

    Blog getBlog(Long id);

    //此处多了一个参数  查询条件封装成一个Blog对象
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

}
