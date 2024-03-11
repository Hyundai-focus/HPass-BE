package com.hyundai.hpass.service;

import java.util.List;

import com.hyundai.hpass.dto.TodayStoreInfoDTO;
import com.hyundai.hpass.dto.TodayStoreVisitResDto;
import com.hyundai.hpass.dto.TodayVisitStore;

public interface TodayStoreService {
	List<TodayStoreVisitResDto> todayStoreVisitList(Long memberNo); //사용자의 방문 판별 및 오늘의 상점 정보
	TodayStoreVisitResDto userVisitStore(Long memberNo, Long storeNo); //유저 방문 처리
	Long userVisitStoreNum(Long memberNo); //유저가 방문한 상점 개수
	List<Long> userVisitFloor(Long memberNo);
	
	// 작성자: 황수연
	public List<TodayVisitStore> getTodayStore();
	
	// 작성자: 황수연
	public List<TodayVisitStore> getVisitStore();
	TodayStoreInfoDTO getTodayStoreInfo();

}
