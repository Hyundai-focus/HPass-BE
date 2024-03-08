package com.hyundai.hpass.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hyundai.hpass.dto.TodayStoreInfoDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.hpass.domain.Store;
import com.hyundai.hpass.dto.TodayStoreVisitResDto;
import com.hyundai.hpass.dto.TodayVisitStore;
import com.hyundai.hpass.mapper.StoreMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
@RequiredArgsConstructor
public class TodayStoreServiceImplement implements TodayStoreService {
	private final StoreMapper storeMapper;
	@Override
	public List<TodayStoreVisitResDto> todayStoreVisitList(Long memberNo) {
		List<TodayStoreVisitResDto> userTodayStore  = new ArrayList<>();
		List<Long> todayList = storeMapper.selectTodayStore();
		for (Long storeNo : todayList){
			List<Long> userOfTodayStore = storeMapper.memberOfTodayStore(storeNo);
			Store todayStoreInfo = storeMapper.selectTodayStoreInfo(storeNo);
			TodayStoreVisitResDto userRes = new TodayStoreVisitResDto();
			userRes.todayStoreVisitResDto(todayStoreInfo);
			if(userOfTodayStore.contains(memberNo)){
				if(userTodayStore.stream().anyMatch(dto -> dto.getStoreBrand().equals(userRes.getStoreBrand()))) continue;
				userRes.setTodayStoreStatus(true);
			}
			userTodayStore.add(userRes);
		}
		return userTodayStore;
	}

	@Override
	public TodayStoreVisitResDto userVisitStore(Long memberNo, Long storeNo) {
		List<Long> userOfTodayStore = storeMapper.memberOfTodayStore(storeNo);
		List<Long> todayList = storeMapper.selectTodayStore(); //오늘의 상점 리스트
		if(!todayList.contains(storeNo)){
			return TodayStoreVisitResDto.builder()
				.storeFloor("not today")
				.build();
		}
		if(!userOfTodayStore.contains(memberNo)){ //아직 방문하지 않았다면 방문처리
			storeMapper.insertTodayStoreMember(storeNo,memberNo);
			TodayStoreVisitResDto storeInfo = new TodayStoreVisitResDto();
			storeInfo.todayStoreVisitResDto(storeMapper.selectTodayStoreInfo(storeNo));
			storeInfo.setTodayStoreStatus(true);
			return storeInfo;
		}
		else{ //이미 방문한 경우
			return TodayStoreVisitResDto.builder()
				.storeImg("")
				.storeBrand("")
				.visitStatus(false)
				.storeFloor("already")
				.build();
		}
	}
	@Override
	public List<Long> userVisitFloor(Long memberNo) {
		return storeMapper.memberFloor(memberNo);
	}

	@Override
	public Long userVisitStoreNum(Long memberNo) {
		return storeMapper.memberStoreVisitNum(memberNo);
	}

	@Override
	public List<TodayVisitStore> getTodayStore() {
		return storeMapper.getTodayStore();
	}

	@Override
	public List<TodayVisitStore> getVisitStore() {
		return storeMapper.getVisitStore();
	}

	@Override
	public TodayStoreInfoDTO getTodayStoreInfo() {
		return storeMapper.getTodayStoreInfo();
	}

}

