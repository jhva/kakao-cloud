package com.movie.springmovieproject.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component //bean을 자동으로 생성해주는 어노테이션
// controlelr service repository restcontroller Configureation
public class EmployeServiceAspect {

    @Before(value = "execution(* com.movie.springmovieproject.service.EmployesService.*(..)) and args(empId,fname,sname)")
    public void beforeAdvice(JoinPoint joinPoint, String empId, String fname, String sname) {
        System.out.println("메서드 호출하기 전에 호출 ");
    }


    @Before(value = "execution(* com.movie.springmovieproject.service.EmployesService.*(..)) and args(empId,fname,sname)")
    public void afterAdvice(JoinPoint joinPoint, String empId, String fname, String sname) {
        System.out.println("메서드 호출후 전에 호출 ");
    }
}
