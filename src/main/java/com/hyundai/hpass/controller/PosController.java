package com.hyundai.hpass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.hpass.dto.PosProductInfoDTO;
import com.hyundai.hpass.dto.PosProductInfoReqDTO;
import com.hyundai.hpass.service.PosService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/pos")
@RequiredArgsConstructor
@Log4j2
public class PosController {
	private final PosService posService;

	@PostMapping("/product/new/")
	public ResponseEntity<List<PosProductInfoDTO>> todayStoreList(
		Authentication authentication,
		@RequestBody PosProductInfoReqDTO store
	) {
		List<PosProductInfoDTO> res = posService.prodList((long)Integer.parseInt(authentication.getName()), store.getStoreName());
		return ResponseEntity.ok().body(res);
	}
}
