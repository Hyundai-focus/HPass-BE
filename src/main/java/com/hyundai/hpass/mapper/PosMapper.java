package com.hyundai.hpass.mapper;

import java.util.List;

import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.dto.PosProductReceiveStatusDTO;
import com.hyundai.hpass.dto.PosProductStatusResDTO;
import com.hyundai.hpass.dto.PosProductUserDTO;

public interface PosMapper {
	Long getPosRole(Long posNum);
	List<Product> getProductList(String storeName);
	PosProductReceiveStatusDTO receiveStatus(Long productNo);
	List<PosProductStatusResDTO> receiveByDate(Long prodNo);
	List<PosProductUserDTO> productHistory(Long prodNo);
}
