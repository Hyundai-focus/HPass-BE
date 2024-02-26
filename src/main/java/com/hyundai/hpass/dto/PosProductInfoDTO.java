package com.hyundai.hpass.dto;

import java.util.Arrays;
import java.util.List;

import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.domain.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PosProductInfoDTO {
	private Long prodNo;
	private String prodImg;
	private String prodName;
	private String prodBrand;
	private int totalStock;
	private int applyStock;
	private int receiveStock;

	public PosProductInfoDTO (Product product, PosProductReceiveStatusDTO status){
		this.prodNo = product.getProductNo();
		this.prodImg = product.getProductImg();
		this.prodName = product.getProductName();
		this.prodBrand = product.getProductBrand();
		this.totalStock = product.getStock() + status.getNotReceive() + status.getReceive();
		this.applyStock = status.getReceive() + status.getNotReceive();
		this.receiveStock = status.getReceive();
	}
}
