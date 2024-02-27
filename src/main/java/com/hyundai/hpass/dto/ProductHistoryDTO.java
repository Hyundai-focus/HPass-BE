package com.hyundai.hpass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
작성자: 황수연
처리 내용: 신제품 관리자 페이지용 DTO
*/
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductHistoryDTO {
	private Long productHistoryNo;
	private Long memberNo;
	private Long productNo;
	private String productHistoryDt;
	private String productStatus;
	private int dailyProductCount;
	private int cumulativeProductCount;
}
