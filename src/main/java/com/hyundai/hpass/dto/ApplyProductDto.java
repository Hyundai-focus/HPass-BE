package com.hyundai.hpass.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplyProductDto {
	private Long productNo;
	private String dummy; //파싱용 더미값
}
