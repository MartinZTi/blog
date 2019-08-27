package com.mzt.service;

import com.mzt.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/26 13:34
 * @Description : com.mzt.service
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id, Type type);

    void deleteType(Long id);

    //相同名称分类不得入库的  校验
    Type getTypeByName(String name);


}
