package com.example.boardtest.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf 토큰 해제
                    .authorizeRequests() //url별 권한 관리를 설정하는 옵션
                    .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll() //antMatchers() : 권한 관리 대상을 지정하는 옵션 "/" 등 지정된 URL들은 모두 permitAll() 메소드를 통해 전체 열람 권한을 주었습니다.
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/user/login")
                //.loginProcessingUrl("/auth/api/v1/user/login")
                    .defaultSuccessUrl("/");
    }
}
