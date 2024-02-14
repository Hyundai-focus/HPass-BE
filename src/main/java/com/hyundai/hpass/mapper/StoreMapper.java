package com.hyundai.hpass.mapper;

import java.util.List;

import com.hyundai.hpass.domain.Store;
import com.hyundai.hpass.domain.TodayStore;

public interface StoreMapper {
	Long selectOneForFloor(Long floor);

	void insertTodayStore(Long todayStore);

	List<Long> selectTodayStore();
	Long selectFloorByStoreNo(Long storeNo);

	void deleteTodayStore(Long storeNo);
}
