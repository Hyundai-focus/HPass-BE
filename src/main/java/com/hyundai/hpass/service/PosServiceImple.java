package com.hyundai.hpass.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.dto.PosProductInfoDTO;
import com.hyundai.hpass.dto.PosProductReceiveStatusDTO;
import com.hyundai.hpass.dto.PosProductStatusResDTO;
import com.hyundai.hpass.dto.PosProductUserDTO;
import com.hyundai.hpass.dto.ProductUserInfoDTO;
import com.hyundai.hpass.mapper.PosMapper;
import com.hyundai.hpass.websocket.HpassWebSocketHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
@RequiredArgsConstructor
public class PosServiceImple implements PosService{
	private final PosMapper posMapper;
	private final ProductService productService;
	private final HpassWebSocketHandler webSocketHandler;

	@Override
	public List<PosProductInfoDTO> prodList(Long posNum, String storeName) {
		List<PosProductInfoDTO> res = new ArrayList<>();
		if(!checkPos(posNum)) return res;
		List<Product> canReceive = posMapper.getProductList(storeName);
		for(Product prod : canReceive){
			PosProductReceiveStatusDTO status = posMapper.receiveStatus(prod.getProductNo());
			res.add(new PosProductInfoDTO(prod, status));
		}
		return res;
	}

	@Override
	public List<PosProductStatusResDTO> prodStatus(Long posNum, Long prodNo) {
		List<PosProductStatusResDTO> res = new ArrayList<>();
		if(!checkPos(posNum)) return res;
		res = posMapper.receiveByDate(prodNo);
		return res;
	}

	@Override
	public List<PosProductUserDTO> prodUserList(Long posNum, Long productNo) {
		List<PosProductUserDTO> res = posMapper.productHistory(productNo);
		if(!checkPos(posNum)) return res;
		for(PosProductUserDTO prodHis : res){
			prodHis.setStatus(prodHis.getProductStatus());
		}
		return res;
	}

	@Override
	public Boolean prodUserCheck(Long memberNo) {
		ProductUserInfoDTO userHis = productService.getProductHistory(memberNo);
		userHis.setName(userHis.getMemberName());
		String userHisString = userHis.toString();
		try {
			webSocketHandler.sendProdRes(userHisString);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public Boolean checkPos(Long posNum){
		return posMapper.getPosRole(posNum) == 3;
	}
}
