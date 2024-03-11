package com.hyundai.hpass.mapper;

import java.util.List;

import com.hyundai.hpass.dto.TodayStoreInfoDTO;
import org.apache.ibatis.annotations.Param;

import com.hyundai.hpass.domain.Store;
import com.hyundai.hpass.dto.TodayVisitStore;

public interface StoreMapper {
	// 작성자 : 김은서
	Long selectOneForFloor(Long floor);
	// 작성자 : 김은서
	void insertTodayStore(Long todayStore);
	// 작성자 : 김은서
	List<Long> selectTodayStore();
	// 작성자 : 김은서
	Long selectFloorByStoreNo(Long storeNo);
	// 작성자 : 김은서
	void deleteTodayStore(Long storeNo);
	// 작성자 : 김은서
	List<Long> memberOfTodayStore(Long storeNo);
	// 작성자 : 김은서
	Store selectTodayStoreInfo(Long storeNo);
	// 작성자 : 김은서
	void insertTodayStoreMember(@Param("storeNo") Long storeNo, @Param("memberNo") Long memberNo);
	// 작성자 : 김은서
	Long memberStoreVisitNum(Long memberNo);
	// 작성자 : 김은서
	List<Long> memberFloor(Long memberNo);

	// 작성자: 황수연
	public List<TodayVisitStore> getTodayStore();

	// 작성자: 황수연
	public List<TodayVisitStore> getVisitStore();
	// 작성자 : 최현서
	TodayStoreInfoDTO getTodayStoreInfo();
}
