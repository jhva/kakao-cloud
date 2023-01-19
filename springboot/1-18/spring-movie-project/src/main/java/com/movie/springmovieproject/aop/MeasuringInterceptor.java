package com.movie.springmovieproject.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Log4j2
public class MeasuringInterceptor implements HandlerInterceptor {
    @Bean
    @Override
    //컨트롤러에게 요청을 하기전에 호출되는 메서드
    //리턴이 false면 컨트롤러에게 요청을 전달하지않음
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //request는 세션을 찾아올수있다

        log.warn("컨트롤러가 요청을 처리하기 전에 호출 ");
        return true;
    }


    @Bean

    @Override
    //컨트롤러가 요청을 처리한 후
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //로그 기록WWWWwWw
        log.warn("요청을 정상적으로 처리한 후 호출");
    }

    @Override
    //Controller가 요청을 처리한 후 무조건 호출되는 메서드
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
