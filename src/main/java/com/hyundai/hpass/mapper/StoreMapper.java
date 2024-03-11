package com.hyundai.hpass.mapper;

import java.util.List;

import com.hyundai.hpass.dto.TodayStoreInfoDTO;
import org.apache.ibatis.annotations.Param;

import com.hyundai.hpass.domain.Store;
import com.hyundai.hpass.dto.TodayVisitStore;

public interface StoreMapper {
	Long selectOneForFloor(Long floor);

	void insertTodayStore(Long todayStore);

	List<Long> selectTodayStore();

	Long selectFloorByStoreNo(Long storeNo);

	void deleteTodayStore(Long storeNo);

	List<Long> memberOfTodayStore(Long storeNo);

	Store selectTodayStoreInfo(Long storeNo);

	void insertTodayStoreMember(@Param("storeNo") Long storeNo, @Param("memberNo") Long memberNo);

	Long memberStoreVisitNum(Long memberNo);

	List<Long> memberFloor(Long memberNo);

	// 작성자: 황수연
	public List<TodayVisitStore> getTodayStore();

	// 작성자: 황수연
	public List<TodayVisitStore> getVisitStore();
	TodayStoreInfoDTO getTodayStoreInfo();
}
