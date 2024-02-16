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
public class Product {
	private Long productNo;
	private String productBrand;
	private String productName;
	private String productImg;
	private int stock;
	private String receiveDt;
	private String receiveLoc;
}
