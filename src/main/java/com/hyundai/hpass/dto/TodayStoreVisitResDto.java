package com.hyundai.hpass.dto;

import lombok.Builder;

@Builder
public class TodayStoreVisitResDto {
	private String storeBrand;
	private String storeImg;
	private String storeFloor; // 1F Exclusive Label 와 같은 문자열
	private Boolean visitStatus; //방문한 경우 true, 방문하지 않은 경우 false
}
