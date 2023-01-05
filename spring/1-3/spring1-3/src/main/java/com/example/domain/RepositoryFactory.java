package com.example.domain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//인스턴스를 생성해주는 Factory클래스
//팩토리 클래스라는 어노테이션
@Configuration
public class RepositoryFactory {
    //create 대신에 newInstance사용해도 같은의미
    //매번 인스턴스를 생성해서 제공


    //@Bean 은 인스턴스를 만들어주는 메서드
    @Bean
    public static ItemRepository create() {
        return new ItemRepository();
    }
}
