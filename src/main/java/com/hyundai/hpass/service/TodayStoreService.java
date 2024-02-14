package com.hyundai.hpass.service;

import java.util.List;

import com.hyundai.hpass.dto.TodayStoreVisitResDto;

public interface TodayStoreService {

	void todayStoreList(); //오늘의 상점 선별
	List<TodayStoreVisitResDto> todayStoreVisitList(Long memberNo); //사용자의 방문 판별 및 오늘의 상점 정보

}
