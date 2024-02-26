package com.hyundai.hpass.service;

import java.util.List;

import com.hyundai.hpass.dto.PosProductInfoDTO;

public interface PosService {
	List<PosProductInfoDTO> prodList(Long posNum, String storeName);
}
