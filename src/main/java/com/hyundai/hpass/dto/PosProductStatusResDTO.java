package com.hyundai.hpass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PosProductStatusResDTO {
	private int notReceive;
	private int receive;
	private String historyDate;
	private Long productNo;
}
