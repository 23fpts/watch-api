package com.thc.watchapi.config;

import com.thc.watchapi.config.interceptor.AreaInterceptor;
import com.thc.watchapi.config.interceptor.WebTokenInterceptor;
import com.thc.watchapi.config.resolver.CurrentUserArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.config
 * @Description: 配置几个interceptors 和 resolvers
 * @date 2020/11/12 5:15 下午
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    @Bean
    public AreaInterceptor getAreaInterceptor() {
        return new AreaInterceptor();
    }

    @Bean
    public WebTokenInterceptor getWebTokenInterceptor() {
        return new WebTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 处理浏览器跨域访问拦截器
        registry.addInterceptor(getAreaInterceptor()).addPathPatterns("/**");
        // 管理端Token验证拦截器
        registry.addInterceptor(getWebTokenInterceptor()).addPathPatterns("/api/v1/web/**")
                .excludePathPatterns("/api/v1/web/login/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserArgumentResolver());
    }

    @Bean
    public CurrentUserArgumentResolver currentUserArgumentResolver() {
        return new CurrentUserArgumentResolver();
    }
}