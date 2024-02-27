package com.hyundai.hpass.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {
	private long storeNo;
	private String storeBrand;
	private int storeFloor;
	private String storeImg;
	private String storeMap;
}
