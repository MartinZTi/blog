package com.mzt.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/26 12:49
 * @Description : com.mzt.web.admin
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @RequestMapping("/blogs")
    public String blogs(){
        return "admin/blogs";
    }



}
