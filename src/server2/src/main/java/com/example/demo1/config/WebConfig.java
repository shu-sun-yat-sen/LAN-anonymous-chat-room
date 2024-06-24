package com.example.demo1.config;

import com.example.demo1.interceptors.Logininterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private Logininterceptor logininterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logininterceptor)
                .excludePathPatterns("/user/login", "/user/register", "/**/*.html", "/**/*.css", "/**/*.js")
                .excludePathPatterns("/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.gif")
                .excludePathPatterns("/**/*.svg", "/**/*.ico", "/**/*.ttf", "/**/*.woff", "/**/*.woff2")
                .excludePathPatterns("/favicon.ico", "/error")
                .excludePathPatterns("/**/*.zip", "/**/*.rar", "/**/*.7z", "/**/*.tar", "/**/*.gz")
                .excludePathPatterns("/**/*.pdf", "/**/*.doc", "/**/*.docx", "/**/*.xls", "/**/*.xlsx")
                .excludePathPatterns("/**/*.ppt", "/**/*.pptx", "/**/*.txt", "/**/*.csv", "/**/*.xml")
                .excludePathPatterns("/**/*.mp3", "/**/*.wav", "/**/*.ogg", "/**/*.mp4", "/**/*.avi", "/**/*.mkv")
                .excludePathPatterns("/**/*.exe", "/**/*.bat", "/**/*.sh", "/**/*.bin", "/**/*.iso");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*", "http://localhost:8081", "http://127.0.0.1:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // “file:” 为固定内容，经测试发现，不可更改
        registry.addResourceHandler("/files/**").addResourceLocations("file:///D:/h2/");
    }
}
