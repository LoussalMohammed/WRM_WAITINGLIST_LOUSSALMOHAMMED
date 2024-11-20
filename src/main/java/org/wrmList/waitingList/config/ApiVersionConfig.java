package org.wrmList.waitingList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.RestController;

import static org.wrmList.waitingList.shared.constant.ApiConstants.API_BASE_PATH;


@Configuration
public class ApiVersionConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                configurer.addPathPrefix(API_BASE_PATH,
                        c -> c.isAnnotationPresent(RestController.class));
            }
        };
    }
}