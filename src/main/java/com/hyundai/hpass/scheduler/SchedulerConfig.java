package com.hyundai.hpass.scheduler;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

// 작성자: 김은서
// 작성 내용: 스케쥴러 / 잠금 설정
@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30M") // Java 8의 Duration 포맷 사용
public class SchedulerConfig {
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(1); // 쓰레드 풀 크기를 1로 설정
		return scheduler;
	}

	@Bean
	public LockProvider lockProvider(DataSource dataSource) {
		return new JdbcTemplateLockProvider(
			JdbcTemplateLockProvider.Configuration.builder()
				.withJdbcTemplate(new JdbcTemplate(dataSource))
				.usingDbTime() // DB 시간을 사용
				.build()
		);
	}
}
