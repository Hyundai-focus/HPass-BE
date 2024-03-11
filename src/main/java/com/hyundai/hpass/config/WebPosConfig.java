package com.hyundai.hpass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 작성자: 김은서
 처리 내용: 포스기 설정
 */
@Configuration
public class WebPosConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000", "https://h-pass-react-admin.vercel.app")
			.allowCredentials(true);
	}
}
