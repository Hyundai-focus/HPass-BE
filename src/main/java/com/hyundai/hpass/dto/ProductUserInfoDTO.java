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
	private Long productHistoryNo;
	private Boolean status;
	private String memberName;
	private String prodImg;
	private String prodName;
	private String prodBrand;
	private String receiveDt;
	private String receiveLoc;
	public void setName(String name){
		if(name.length() == 2) this.memberName = name.charAt(0) + "*";
		else this.memberName = name.charAt(0) + "*" + name.charAt(2);
	}
}
