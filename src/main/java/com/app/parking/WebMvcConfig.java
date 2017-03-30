package com.app.parking;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by gatomulesei on 3/30/2017.
 */
@Profile("REST")
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    /**
     * Set up the CORS headers so that all of the REST endpoints
     * from the frontend app installed on another server can be used
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/rest/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("DELETE", "POST", "GET", "OPTIONS")
                .allowCredentials(true).maxAge(3600);
    }
}