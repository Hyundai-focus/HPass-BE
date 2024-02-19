package com.hyundai.hpass.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductHistory {
	private Long productHistoryNo;
	private Long memberNo;
	private Long productNo;
	private String productHistoryDt;
	private Boolean productStatus;
}
