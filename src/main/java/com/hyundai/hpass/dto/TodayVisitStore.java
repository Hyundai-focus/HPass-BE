package com.hyundai.hpass.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodayVisitStore {
	private long todayStoreNo;
	private int storeNo;
	private LocalDate todayStoreDt;
	private long memberNo;
	private String storeBrand;
}
