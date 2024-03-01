package com.hyundai.hpass.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import com.hyundai.hpass.mapper.StoreMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SchedulerHandler {
	private final StoreMapper storeMapper;

	@Scheduled(cron = "0 1 0 * * *")
	@SchedulerLock(name = "scheduledTaskName", lockAtMostFor = "5m", lockAtLeastFor = "3m") //잠금 최소 3~ 최대 5분 유지
	public void todayStoreList() {
		for (long i = 1L; i <= 5; i++) {
			Long todayStore = storeMapper.selectOneForFloor(i);
			storeMapper.insertTodayStore(todayStore);
		}
	}
}
