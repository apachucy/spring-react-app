package com.miciu.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String NO_PASSWORD_ENCODING = "{noop}";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        User.UserBuilder users = User.builder();
        auth.inMemoryAuthentication()
                .withUser(users.username("admin").password(NO_PASSWORD_ENCODING + "admin").roles("ADMIN"))
                .withUser(users.username("users").password(NO_PASSWORD_ENCODING + "users").roles("USERS"));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/employee");
    }
}
