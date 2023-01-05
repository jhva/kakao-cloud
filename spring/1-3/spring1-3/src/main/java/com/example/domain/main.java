package com.example.domain;

import org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main {
    public static void main(String[] args) {
        System.out.println("123");
        //인스턴스 생성을 다른 팩토리 클래스를 이용해서 생성
        //다른 클래스의 메서드를 이용해서 생성하는것을
        //factorty method pattern이라고함

        //스프링이 인스턴스를 생성
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryFactory.class);


        //RepositoryFactory 클래스의 create메서드를 호출해서
        //ItemRepository클래스의 인스턴스르 생성
        ItemRepository itemRepository3 = context.getBean("create", ItemRepository.class);
        ItemRepository itemRepository4 = context.getBean("create", ItemRepository.class);
        ItemRepository itemRepository = RepositoryFactory.create();
        ItemRepository itemRepository1 = RepositoryFactory.create();
        Item item = itemRepository.get();
        System.out.println(System.identityHashCode(itemRepository3));
        System.out.println(System.identityHashCode(itemRepository4));
        System.out.println(itemRepository3);

    }
}
