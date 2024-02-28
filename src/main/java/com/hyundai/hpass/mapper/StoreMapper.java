package com.hyundai.hpass.mapper;

import java.util.List;

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

	public List<TodayVisitStore> getTodayStore();

	public List<TodayVisitStore> getVisitStore();
}
