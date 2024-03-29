package com.hyundai.hpass.service;

import java.time.LocalDate;
import java.util.List;

import com.hyundai.hpass.dto.AdminRegisterProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.domain.ProductHistory;
import com.hyundai.hpass.dto.ProductHistoryDTO;
import com.hyundai.hpass.dto.ProductUserInfoDTO;
import com.hyundai.hpass.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImplement implements ProductService {
	private final ProductMapper productMapper;
	@Override
	public List<Product> getProductList() {
		return productMapper.selectAllProduct();
	}

	ProductHistory getUserHistory(Long memberNo){
		List<ProductHistory> userHistorys = productMapper.selectValidUserHistory(memberNo); //유저의 신청 내역
		ProductHistory validProductHistory = null;
		for(ProductHistory userHistory : userHistorys){
			long userProductNo = userHistory.getProductNo();
			//제품의 수령 기간이 오늘보다 이전이면 신청 내역에서 제외
			LocalDate now = LocalDate.now();
			LocalDate endDate = productMapper.selectProductReceiveDt(userProductNo);
			if(endDate.isBefore(now))continue;
			validProductHistory = userHistory;
		}
		return validProductHistory;
	}
	@Override
	public String applyProduct(Long productNo, Long memberNo) {
		ProductHistory validProductHistory = getUserHistory(memberNo);
		if(validProductHistory == null){
			Product productInfo = productMapper.selectProductInfo(productNo);
			if(productInfo.getStock() > 0){
				productMapper.updateProductStock(productNo);
				productMapper.insertProductHistory(productNo,memberNo);
				return "success";
			}
			else return "Not in stock";
		}
		else return "Application history exists"; //이미 신청 내역이 존재
	}

	@Override
	public ProductUserInfoDTO getProductHistory(Long memberNo) {
		ProductHistory userHis = getUserHistory(memberNo);
		String name = productMapper.selectMemberName(memberNo);
		if(userHis == null){
			return ProductUserInfoDTO.builder()
				.status(false)
				.memberName(name)
				.build();
		}
		else{
			Product productInfo = productMapper.selectProductInfo(userHis.getProductNo());
			return ProductUserInfoDTO.builder()
				.status(true)
				.productHistoryNo(userHis.getProductHistoryNo())
				.memberName(name)
				.prodImg(productInfo.getProductImg())
				.prodName(productInfo.getProductName())
				.receiveDt(productInfo.getReceiveDt())
				.prodBrand(productInfo.getProductBrand())
				.receiveLoc(productInfo.getReceiveLoc())
				.build();
		}
	}

	@Override
	public String cancelProductApply(Long memberNo) {
		ProductHistory userHis = getUserHistory(memberNo);
		if(userHis == null) return "No History";
		productMapper.updateProductHistoryStatus(userHis.getProductHistoryNo(), "cancel");
		productMapper.updateCancelProductStock(userHis.getProductNo());
		return "success";
	}

	@Override
	public Boolean receiveProduct(Long memberNo) {
		ProductHistory userHis = getUserHistory(memberNo);
		if(userHis == null) return false;
		productMapper.updateProductHistoryStatus(userHis.getProductHistoryNo(), "true");
		return true;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return productMapper.totalCnt(cri);
	}

	@Override
	public List<ProductHistoryDTO> getProductsList(Criteria cri) {
		return productMapper.getProductHistory(cri);
	}

	@Override
	public List<ProductHistoryDTO> getReceiveList(Criteria cri) {
		return productMapper.getReceiveHistory(cri);
	}

	@Override
	public List<ProductHistoryDTO> getCountProduct() {
		return productMapper.getCountProduct();
	}

	@Override
	public List<ProductHistoryDTO> getSumProduct() {
		return productMapper.getSumProduct();
	}

	@Override
	public int insertProduct(Product product) {
		return productMapper.insertProduct(product);
	}

	@Override
	public boolean deleteProduct(int productNo) {
		int result = productMapper.deleteProduct(productNo);
		return result == 1;
	}

	@Override
	public List<AdminRegisterProductDTO> getRegisterList() {
		return productMapper.getRegisterList();
	}

}
