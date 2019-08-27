package com.mzt.service;

import com.mzt.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/27 12:29
 * @Description : com.mzt.service
 */
public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);

    //相同名称tag不得入库的  校验
    Tag getTagByName(String name);


}
