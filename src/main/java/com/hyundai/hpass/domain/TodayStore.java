package com.hyundai.hpass.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodayStore {
	private long todayStoreNo;
	private int storeNo;
	private LocalDate todayStoreDt;
	private long memberNo;
}
