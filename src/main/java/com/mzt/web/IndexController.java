package com.mzt.web;

import com.mzt.sysException.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/24 21:48
 * @Description : com.mzt.web
 */
@Controller
public class IndexController {

    @RequestMapping({"/","index"})
    public String index(){
//        int i = 9/0;
//        String blog = null;
//        if(blog == null){
//            throw new NotFoundException("博客不存在");
//        }

        return "index";
    }

    /*测试切面*/
    @RequestMapping("/{id}/{name}")
    public String testAOP(@PathVariable Integer id, @PathVariable String name){
        System.out.println("----------index-----------");
        return "index";
    }



}
