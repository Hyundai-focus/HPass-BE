package com.hyundai.hpass.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
			Thread.sleep(3000); // 연산 대기
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
		floorAndStoreNoList.sort((tuple1, tuple2) -> { //정렬
			Long floor1 = tuple1.keySet().iterator().next();
			Long floor2 = tuple2.keySet().iterator().next();
			return floor1.compareTo(floor2);
		});
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
		return null;
	}
}
