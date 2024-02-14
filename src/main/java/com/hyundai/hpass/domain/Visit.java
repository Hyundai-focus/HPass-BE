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
public class Visit {
	private long visitNo;
	private long memberNo;
	private long todayStoreNo;
	private LocalDate visitDt;
}
