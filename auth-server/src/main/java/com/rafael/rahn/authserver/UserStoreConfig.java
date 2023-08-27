package com.rafael.rahn.authserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserStoreConfig {

    @Bean
    UserDetailsService userDetailsService() {
        // UserDetailsService faz parte do spring e eh responsavel por consultar usuarios
        // no processo de autenticacao

        // configura para utilizacao dos usuario em memoria

        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(
                User.withUsername("user")
                        .password("{noop}password") // {noop} para que seja armazenado em texto puro
                        .roles("USER")
                        .build()
        );

        return userDetailsManager;
    }
}
