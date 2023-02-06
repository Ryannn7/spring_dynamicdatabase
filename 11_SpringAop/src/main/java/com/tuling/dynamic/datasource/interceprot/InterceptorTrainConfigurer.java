package com.tuling.dynamic.datasource.interceprot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liuzongshuai
 * @date 2023/1/28 20:12
 */
@Configuration
public class InterceptorTrainConfigurer implements WebMvcConfigurer {


    /**
     * addPathPatterns("/**") 表示拦截所有的请求，
     * addPathPatterns("/**") 表示拦截所有的请求，
     * addPathPatterns("/test/**") 表示拦截/test/ 下的所有路径请求，
     * addPathPatterns("/test/*") 表示拦截/test/abc，拦截/test/aaa , 不拦截 /test/abc/def
     * addPathPatterns("/test/**").excludePathPatterns("/test/login", “/test/register”) 表示拦截/test/ 下的所有路径请求，但不拦截 /test/login 和 /test/registe
     * @param registry
     */
    @Override
    public  void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new AnnoInterceptor()).addPathPatterns("/**");

    }

}
