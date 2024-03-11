package com.hyundai.hpass.service;

import java.util.List;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.dto.AdminRegisterProductDTO;
import com.hyundai.hpass.dto.ProductHistoryDTO;
import com.hyundai.hpass.dto.ProductUserInfoDTO;
// 작성자 : 김은서
public interface ProductService {
	List<Product> getProductList();

	String applyProduct(Long productNo, Long memberNo);

	ProductUserInfoDTO getProductHistory(Long memberNo);

	String cancelProductApply(Long memberNo);

	Boolean receiveProduct(Long memberNo);
	
	// 작성자: 황수연
	public int getTotalCnt(Criteria cri);
	
	// 작성자: 황수연
	public List<ProductHistoryDTO> getProductsList(Criteria cri);
	
	// 작성자: 황수연
	public List<ProductHistoryDTO> getReceiveList(Criteria cri);
	
	// 작성자: 황수연
	public List<ProductHistoryDTO> getCountProduct();
	
	// 작성자: 황수연
	public List<ProductHistoryDTO> getSumProduct();
	int insertProduct(Product product);
	boolean deleteProduct(int productNo);
	List<AdminRegisterProductDTO> getRegisterList();
}
