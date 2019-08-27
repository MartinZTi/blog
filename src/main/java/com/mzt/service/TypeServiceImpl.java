package com.mzt.service;

import com.mzt.dao.TypeDao;
import com.mzt.pojo.Type;
import com.mzt.sysException.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/26 13:37
 * @Description : com.mzt.service
 */
@Service
public class TypeServiceImpl  implements  TypeService{

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        Optional<Type> typeOptional = typeDao.findById(id);
        Type type = typeOptional.orElse(null);
        return type;
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeDao.findById(id).orElse(null);
        if(t == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeDao.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteById(id);
    }

    //根据名称查询DB
    @Override
    public Type getTypeByName(String name) {

        return typeDao.findByName(name);
    }
}
