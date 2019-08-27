package com.mzt.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version :1.0
 * @Author :29988
 * @Date : 2019/8/26 13:06
 * @Description : com.mzt.interceptor
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new LoginInterceptor())
                 .addPathPatterns("/admin/**")
                 .excludePathPatterns("/admin")
                 .excludePathPatterns("/admin/login");
    }
}

