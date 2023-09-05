package com.cy.store.config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//处理器拦截器的注册
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        HandlerInterceptor interceptor = new LoginInterceptor();
        //设置白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");//样式文件等都要，不然的话用不了
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/admRegister.html");
        patterns.add("/web/login.html");
        patterns.add("/web/admLogin.html");
        patterns.add("/web/index.html");
        patterns.add("/web/goods.html");
        patterns.add("/web/search_catelog.html");
        patterns.add("/web/search.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/admin/reg");
        patterns.add("/admin/get_new_notice");
        patterns.add("/admin/login");
        patterns.add("/goods/**");

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")//表示要拦截的网址，/**表示所有
                .excludePathPatterns(patterns);//除了，即白名单
    }
}
