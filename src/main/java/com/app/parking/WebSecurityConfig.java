package com.app.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by gatomulesei on 3/30/2017.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_LIST = { "/rest/users/registration" };

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()//allow CORS option calls
                .antMatchers(WHITE_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

}
