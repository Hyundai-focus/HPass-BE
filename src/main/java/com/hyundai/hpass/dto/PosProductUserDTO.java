package com.hyundai.hpass.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PosProductUserDTO {
	private Long productHistoryNo;
	private String memberName;
	private String historyDate;
	private String productStatus;

	public void setStatus(String status){
		this.productStatus =
			status.equals("cancel") ? "취소"
				: status.equals("true") ? "수령" : "미수령";
	}
}
