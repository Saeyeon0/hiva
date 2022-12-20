package com.example.demosecurity.security;

import com.example.demosecurity.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    private UserService userService;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }



    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(bCryptPasswordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String[] statics = {"/img/**", "/css/**","/js/**","/home",  "/","/registration", "/user-type","/login","/favicon.ico"};

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(statics).permitAll()

                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/professor").hasRole("PROFESSOR ")
                .requestMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/registration").defaultSuccessUrl("/home").permitAll().and()
                .loginPage("/login").defaultSuccessUrl("/home").permitAll().and()



                .logout().logoutSuccessUrl("/?logout").permitAll();

        return http.build();
    }

}
