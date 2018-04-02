package com.github.ting723.config;

import com.github.ting723.interceptor.MockInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author : zhanglianwei
 * Create : 2018/3/27 18:18
 * Update : 2018/3/27 18:18
 * Descriptions :
 *
 * @author zhanglianwei
 */
@Configuration
public class JuliyeMockConfigurer implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getMockInterceptors() {
        return new MockInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMockInterceptors()).addPathPatterns("/**");
    }
}
