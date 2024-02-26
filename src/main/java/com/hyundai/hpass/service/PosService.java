package com.hyundai.hpass.service;

import java.util.List;

import com.hyundai.hpass.dto.PosProductInfoDTO;
import com.hyundai.hpass.dto.PosProductStatusResDTO;
import com.hyundai.hpass.dto.PosProductUserDTO;

public interface PosService {
	List<PosProductInfoDTO> prodList(Long posNum, String storeName);
	List<PosProductStatusResDTO> prodStatus(Long posNum, Long productNo);
	List<PosProductUserDTO> prodUserList(Long posNum, Long productNo);
	Boolean prodUserCheck(Long memberNo);
}
