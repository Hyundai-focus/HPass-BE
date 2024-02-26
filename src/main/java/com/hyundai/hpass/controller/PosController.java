package com.hyundai.hpass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.hpass.dto.PosProductInfoDTO;
import com.hyundai.hpass.dto.PosProductStatusResDTO;
import com.hyundai.hpass.dto.PosProductUserDTO;
import com.hyundai.hpass.service.PosService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/pos")
@RequiredArgsConstructor
@Log4j2
public class PosController {
	private final PosService posService;

	@GetMapping("/product/new/{storeName}")
	public ResponseEntity<List<PosProductInfoDTO>> posProdList(
		Authentication authentication,
		@PathVariable String storeName
	) {
		List<PosProductInfoDTO> res = posService.prodList((long)Integer.parseInt(authentication.getName()), storeName);
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/product/new/status/{prodNo}")
	public ResponseEntity<List<PosProductStatusResDTO>> posProdStatus(
		Authentication authentication,
		@PathVariable Long prodNo
	) {
		List<PosProductStatusResDTO> res = posService.prodStatus((long)Integer.parseInt(authentication.getName()), prodNo);
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/product/new/list/{prodNo}")
	public ResponseEntity<List<PosProductUserDTO>> posHistory(
		Authentication authentication,
		@PathVariable Long prodNo
	) {
		List<PosProductUserDTO> res = posService.prodUserList((long)Integer.parseInt(authentication.getName()), prodNo);
		return ResponseEntity.ok().body(res);
	}
}
