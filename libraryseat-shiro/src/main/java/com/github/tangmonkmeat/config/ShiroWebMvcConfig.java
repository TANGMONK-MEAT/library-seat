package com.github.tangmonkmeat.config;

import com.github.tangmonkmeat.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Shiro 的WebMvc 配置
 *
 * @author zwl
 * @date 2020/12/1 22:23
 */
@Configuration
public class ShiroWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    /**
     * 添加 Jwt 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/login");
    }
}
