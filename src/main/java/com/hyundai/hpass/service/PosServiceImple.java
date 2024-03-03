package com.hyundai.hpass.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.dto.PosCouponInfoDTO;
import com.hyundai.hpass.dto.PosProductInfoDTO;
import com.hyundai.hpass.dto.PosProductReceiveStatusDTO;
import com.hyundai.hpass.dto.PosProductStatusResDTO;
import com.hyundai.hpass.dto.PosProductUserDTO;
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
	public List<PosProductInfoDTO> prodList(Long locNo) {
		List<PosProductInfoDTO> res = new ArrayList<>();
		List<Product> canReceive = posMapper.getProductList(locNo);
		for(Product prod : canReceive){
			PosProductReceiveStatusDTO status = posMapper.receiveStatus(prod.getProductNo());
			if(status == null){
				res.add(new PosProductInfoDTO(prod, PosProductReceiveStatusDTO.builder()
					.Receive(0)
					.notReceive(0)
					.build()));
			}
			else res.add(new PosProductInfoDTO(prod, status));
		}
		return res;
	}

	@Override
	public List<PosProductStatusResDTO> prodStatus(Long prodNo) {
		return posMapper.receiveByDate(prodNo);
	}

	@Override
	public List<PosProductUserDTO> prodUserList(Long productNo) {
		return posMapper.productHistory(productNo);
	}

	@Override
	public List<PosCouponInfoDTO> memberCouponList(Long memberNo) {
		return posMapper.memberCoupon(memberNo);
	}

	@Override
	public Boolean useCoupon(Integer couponHistoryNo) {
		posMapper.updateCouponStatus(couponHistoryNo);
		return true;
	}
}
