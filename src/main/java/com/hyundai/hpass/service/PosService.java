package com.hyundai.hpass.service;

import java.util.List;

import com.hyundai.hpass.dto.PosCouponInfoDTO;
import com.hyundai.hpass.dto.PosProductInfoDTO;
import com.hyundai.hpass.dto.PosProductStatusResDTO;
import com.hyundai.hpass.dto.PosProductUserDTO;

public interface PosService {
	List<PosProductInfoDTO> prodList(Long locNo);
	List<PosProductStatusResDTO> prodStatus(Long productNo);
	List<PosProductUserDTO> prodUserList( Long productNo);
	List<PosCouponInfoDTO> memberCouponList(Long memberNo);
	Boolean useCoupon(Integer couponHistoryNo);
}
