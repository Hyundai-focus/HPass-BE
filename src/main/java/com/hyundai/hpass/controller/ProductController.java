package com.hyundai.hpass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.dto.ApplyProductDto;
import com.hyundai.hpass.dto.ProductUserInfoDTO;
import com.hyundai.hpass.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//작성자 : 김은서
@RestController
@RequestMapping("/product/new")
@RequiredArgsConstructor
@Log4j2
public class ProductController {
	private final ProductService productService;

	@GetMapping("/list")
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> res = productService.getProductList();
		return ResponseEntity.ok().body(res);
	}

	@PostMapping("/apply")
	public ResponseEntity<String> applyNewProduct(
		@RequestBody ApplyProductDto applyProductDto,
		Authentication auth
	){
		String res = productService.applyProduct(applyProductDto.getProductNo(), (long)Integer.parseInt(auth.getName()));
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/apply")
	public ResponseEntity<ProductUserInfoDTO> getNewProductInfo(
		Authentication auth
	){
		ProductUserInfoDTO res = productService.getProductHistory((long)Integer.parseInt(auth.getName()));
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/cancel")
	public ResponseEntity<String> cancelProductHistory(
		Authentication auth
	){
		String res = productService.cancelProductApply((long)Integer.parseInt(auth.getName()));
		return ResponseEntity.ok().body(res);
	}
}
