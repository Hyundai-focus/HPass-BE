package com.hyundai.hpass.service;

import java.util.List;

import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.domain.ProductHistory;

public interface ProductService {
	List<Product> getProductList();

	String applyProduct(Long productNo, Long memberNo);

	Product getProductHistory(Long memberNo);

	String cancelProductApply(Long memberNo);

	Boolean receiveProduct(Long memberNo);
}
