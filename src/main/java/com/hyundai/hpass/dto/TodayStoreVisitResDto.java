package com.hyundai.hpass.dto;

import java.util.Arrays;
import java.util.List;

import com.hyundai.hpass.domain.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodayStoreVisitResDto {
	private String storeBrand;
	private String storeImg;
	private String storeFloor; // 1F Exclusive Label 와 같은 문자열
	private Boolean visitStatus; //방문한 경우 true, 방문하지 않은 경우 false

	public void todayStoreVisitResDto (Store store){
		List<String> floor = Arrays.asList(
			"1F Exclusive Label",
			"2F Modern Mood",
			"3F About Fashion",
			"4F Life & Balance",
			"5F Sounds Forest"
		);
		this.storeBrand = store.getStoreBrand();
		this.storeImg = store.getStoreImg();
		this.storeFloor = floor.get(store.getStoreFloor() - 1);
		this.visitStatus = false;
	}
	public void setTodayStoreStatus(Boolean status){
		this.visitStatus = status;
	}
}
