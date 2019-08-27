package com.mzt.service;

import com.mzt.dao.BlogDao;
import com.mzt.pojo.Blog;
import com.mzt.pojo.Type;
import com.mzt.sysException.NotFoundException;
import com.mzt.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/27 15:15
 * @Description : com.mzt.service
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.findById(id).orElse(null);
    }

    //比较复杂
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        //jpa有提供高级查询
        return blogDao.findAll(new Specification<Blog>() {
            //toPredicate方法用来动态组合查询条件
            /**
             * @param root   代表查询目标对象Blog, 由此可以获取到t_blog表的字段,属性名
             * @param cq     查询条件
             * @param cb     设置具体条件的表达式==/ like / ...
             */
            @Override
            public Predicate toPredicate(Root<Blog> root,
                                         CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                //
                List<Predicate> predicates = new ArrayList<>();
                //条件一:
                if(!"".equals(blog.getTitle()) && blog.getTitle() !=null){
                    predicates.add(cb.like(root.get("title"), "%"+blog.getTitle()+"%"));
                }
                if(blog.getTypeId() != null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }


    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId()  == null){
            //新增时初始化
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }

        return blogDao.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog blogFromDB = blogDao.findById(id).orElse(null);
        if(blogFromDB == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog,blogFromDB);
        return blogDao.save(blogFromDB);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteById(id);
    }
}
