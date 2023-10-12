package com.example.demo.Configuration;

import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    private final Logger logger = Logger.getLogger(SecurityConfiguration.class.getName());

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/login","/register").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(PathRequest.toStaticResources().at(StaticResourceLocation.CSS)).permitAll()
                        .requestMatchers("/create/job").hasAuthority("EMPLOYER")
                        .anyRequest().authenticated()

                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(new AuthHandler())
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
