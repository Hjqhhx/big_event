package com.itheima.config;

import com.itheima.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        //将上面自己定义的拦截器注册进mvc的配置
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/user/login", "/user/register")//放行登录和注册界面
                .excludePathPatterns("/webjars/**", "/v3/api-docs/**", "swagger-resources/**", "/doc.html");//放行knife4j页面
    }


}
