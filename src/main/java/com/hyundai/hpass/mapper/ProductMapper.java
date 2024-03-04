package com.hyundai.hpass.mapper;

import java.time.LocalDate;
import java.util.List;

import com.hyundai.hpass.dto.AdminRegisterProductDTO;
import org.apache.ibatis.annotations.Param;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.domain.ProductHistory;
import com.hyundai.hpass.dto.ProductHistoryDTO;

public interface ProductMapper {
	List<Product> selectAllProduct();

	void updateProductStock(Long productNo);

	void insertProductHistory(@Param("productNo")Long productNo, @Param("memberNo")Long memberNo);

	List<ProductHistory> selectValidUserHistory(Long memberNo);

	String selectMemberName(Long memberNo);

	LocalDate selectProductReceiveDt(Long productNo);

	Product selectProductInfo(Long productNo);

	void updateProductHistoryStatus(@Param("productHistoryNo")Long productHistoryNo, @Param("status")String status);

	void updateCancelProductStock(Long productNo);
	
	// 작성자: 황수연
	public List<ProductHistoryDTO> getProductHistory(Criteria cri);
	
	// 작성자: 황수연
	public List<ProductHistoryDTO> getReceiveHistory(Criteria cri);
	
	// 작성자: 황수연
	public List<ProductHistoryDTO> getCountProduct();
	
	// 작성자: 황수연
	public List<ProductHistoryDTO> getSumProduct();
	
	// 작성자: 황수연
	public int totalCnt(Criteria cri);
	int insertProduct(Product product);
	int deleteProduct(int productNo);
	List<AdminRegisterProductDTO> getRegisterList();

}
