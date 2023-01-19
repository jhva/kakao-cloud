package com.movie.springmovieproject.config;

import com.movie.springmovieproject.aop.MeasuringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//웹 설정 클래스
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //인터셉터 설정 메서드

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //인터셉터가 적용될 url
        registry.addInterceptor(new MeasuringInterceptor())
                .addPathPatterns("/user");
    }
}
