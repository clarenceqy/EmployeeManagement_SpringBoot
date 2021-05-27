package com.synir.paiban.models;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
/**
 * 添加拦截器
 */
public class SessionConfiguration extends WebMvcConfigurerAdapter{
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns("/login")
            .excludePathPatterns("/dologin")
        	.excludePathPatterns("/home")
        	.excludePathPatterns("/css/**")
        	.excludePathPatterns("/img/**");
        
    }
}
