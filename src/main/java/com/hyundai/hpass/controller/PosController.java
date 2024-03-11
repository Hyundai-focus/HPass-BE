package com.hyundai.hpass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.hpass.dto.PosCouponInfoDTO;
import com.hyundai.hpass.dto.PosProductInfoDTO;
import com.hyundai.hpass.dto.PosProductInfoReqDTO;
import com.hyundai.hpass.dto.PosProductStatusResDTO;
import com.hyundai.hpass.dto.PosProductUserDTO;
import com.hyundai.hpass.dto.ProductUserInfoDTO;
import com.hyundai.hpass.service.PosService;
import com.hyundai.hpass.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
/**
 작성자: 김은서
 */
@RestController
@RequestMapping("/pos")
@RequiredArgsConstructor
@Log4j2
public class PosController {
	private final PosService posService;
	private final ProductService productService;

	@GetMapping("/product/new/{locNo}")
	public ResponseEntity<List<PosProductInfoDTO>> posProdList(
		@PathVariable Long locNo
	) {
		List<PosProductInfoDTO> res = posService.prodList(locNo);
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/product/new/status/{prodNo}")
	public ResponseEntity<List<PosProductStatusResDTO>> posProdStatus(
		@PathVariable Long prodNo
	) {
		List<PosProductStatusResDTO> res = posService.prodStatus(prodNo);
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/product/new/list/{prodNo}")
	public ResponseEntity<List<PosProductUserDTO>> posHistory(
		@PathVariable Long prodNo
	) {
		List<PosProductUserDTO> res = posService.prodUserList(prodNo);
		return ResponseEntity.ok().body(res);
	}
	@GetMapping("/product/new/apply/{memberNo}")
	public ResponseEntity<ProductUserInfoDTO> getNewProductInfo(
		@PathVariable Long memberNo
	){
		ProductUserInfoDTO res = productService.getProductHistory(memberNo);
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/product/new/history/{memberNo}")
	public ResponseEntity<Boolean> receiveProductHistory(
		@PathVariable Long memberNo
	){
		Boolean res = productService.receiveProduct(memberNo);
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/coupon/list/{memberNo}")
	public ResponseEntity<List<PosCouponInfoDTO>> memberCouponList(
		@PathVariable Long memberNo
	){
		List<PosCouponInfoDTO> res = posService.memberCouponList(memberNo);
		return ResponseEntity.ok().body(res);
	}

	@PostMapping("/coupon/use")
	public ResponseEntity<Boolean> couponUse(
		@RequestBody PosProductInfoReqDTO reqDTO
	){
		Boolean res = posService.useCoupon(Integer.parseInt(reqDTO.getStoreName()));
		return ResponseEntity.ok().body(res);
	}
}
