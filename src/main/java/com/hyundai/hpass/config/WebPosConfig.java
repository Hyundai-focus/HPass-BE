package com.hyundai.hpass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebPosConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //로컬호스트3000은 개발 끝나면 삭제
			.allowedOrigins("http://localhost:3000", "https://h-pass-react-admin.vercel.app")
			.allowCredentials(true);
	}
}
