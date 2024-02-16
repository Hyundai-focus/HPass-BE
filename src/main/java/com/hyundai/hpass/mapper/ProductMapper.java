package com.hyundai.hpass.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.domain.ProductHistory;

public interface ProductMapper {
	List<Product> selectAllProduct();

	void updateProductStock(Long productNo);

	void insertProductHistory(@Param("productNo")Long productNo, @Param("memberNo")Long memberNo);

	List<ProductHistory> selectValidUserHistory(Long memberNo);

	LocalDate selectProductReceiveDt(Long productNo);

	Product selectProductInfo(Long productNo);

	void updateProductHistoryStatus(@Param("productHistoryNo")Long productHistoryNo, @Param("status")String status);
}
