package com.hyundai.hpass.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.hpass.domain.Store;
import com.hyundai.hpass.dto.TodayStoreVisitResDto;
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
	@Scheduled(cron = "0 1 0 * * *")
	public void todayStoreList() {
		for (long i = 1L; i <= 5; i++) {
			System.out.println(i);
			Long todayStore = storeMapper.selectOneForFloor(i);
			storeMapper.insertTodayStore(todayStore);
		}
		try {
			Thread.sleep(10000); // 연산 대기
		} catch (InterruptedException e) {e.printStackTrace();}
		deleteDuplicate(); //중복 선정 방지
	}

	public void deleteDuplicate(){ //각 층에서 여러번 뽑힌 경우 한 층에 한 상점만 남기기
		List<Long> todayList = storeMapper.selectTodayStore();
		List<Map<Long, Long>> floorAndStoreNoList = new ArrayList<>();
		for (Long storeNo : todayList){
			Map<Long, Long> tuple = new HashMap<>();
			tuple.put(storeMapper.selectFloorByStoreNo(storeNo), storeNo);
			floorAndStoreNoList.add(tuple);
		}
		for(long i = 1L; i < 6; i++){
			final long idx = i;
			List<Map<Long, Long>> matchingTuples = floorAndStoreNoList.stream()
				.filter(tuple -> tuple.keySet().iterator().next().equals(idx))
				.collect(Collectors.toList());

			if(matchingTuples.size() > 1){ //한 층에서 1개 이상 선별되었을 시
				for(int j = 1; j < matchingTuples.size(); j++){
					storeMapper.deleteTodayStore(matchingTuples.get(j).values().iterator().next());
					//오늘의 상점에서 오늘자 - 해당 매장 삭제
				}
			}
		}
	}
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
	public Boolean userVisitStore(Long memberNo, Long storeNo) {
		List<Long> userOfTodayStore = storeMapper.memberOfTodayStore(storeNo);
		if(!userOfTodayStore.contains(memberNo)){ //아직 방문하지 않았다면 방문처리
			storeMapper.insertTodayStoreMember(storeNo,memberNo);
		}
		return true; //if문 돌지 않았음 -> 이미 방문한 사람! 추가 처리하지 않고 방문 했다는 사실을 알려주는 true 반환
	}
}

