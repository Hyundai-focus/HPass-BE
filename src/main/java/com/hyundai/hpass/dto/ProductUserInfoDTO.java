package com.hyundai.hpass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUserInfoDTO {
	private Boolean status;
	private String memberName;
	private String prodImg;
	private String prodName;
	private String prodBrand;
	private String receiveDt;
	private String receiveLoc;
}
