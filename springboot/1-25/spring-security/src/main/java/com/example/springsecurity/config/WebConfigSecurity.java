package com.example.springsecurity.config;

import com.example.springsecurity.exception.Custom403Handler;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


import javax.sql.DataSource;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class WebConfigSecurity {


    private final DataSource dataSource;
    private final CustomUserDetailService customUserDetailService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        //토큰을 어디에저장할건지
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();

        repo.setDataSource(dataSource);
        return repo;
        //이렇게하게되면 자동로그인이 됨 (디비에넣어짐)
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    }

    @Bean
    //필터에서 모든걸 설정함
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity) throws Exception {
        log.info("필터 환경 설정");

        //인증이나 인가에 문제가 발생하면 로그인 폼 출력
        httpSecurity.formLogin().loginPage("/member/login");
        //Oauth2가 사용할 로그인 URL 설정
        httpSecurity.oauth2Login().loginPage("/member/login");

        httpSecurity.csrf().disable();


        httpSecurity.rememberMe()
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(customUserDetailService)
                .tokenValiditySeconds(60 * 60 * 24 * 30);
        httpSecurity.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        return httpSecurity.build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    //정적 파일 요청은 동작하지 않도록설정
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
