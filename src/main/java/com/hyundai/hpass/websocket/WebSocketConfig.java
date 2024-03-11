package com.hyundai.hpass.websocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// 작성자 : 김은서
// 작성 내용 : 소켓 설정
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new HpassWebSocketHandler(), "/socket/*")
			.setAllowedOrigins("https://h-pass-react-admin.vercel.app");
	}
}