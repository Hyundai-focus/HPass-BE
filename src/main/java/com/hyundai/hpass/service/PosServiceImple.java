package com.hyundai.hpass.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.dto.PosProductInfoDTO;
import com.hyundai.hpass.dto.PosProductReceiveStatusDTO;
import com.hyundai.hpass.mapper.PosMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
@RequiredArgsConstructor
public class PosServiceImple implements PosService{
	private final PosMapper posMapper;
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

	public Boolean checkPos(Long posNum){
		if(posMapper.getPosRole(posNum) == 3) return true;
		else return false;
	}
}
