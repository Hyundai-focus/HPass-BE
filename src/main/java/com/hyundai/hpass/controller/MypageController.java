package com.hyundai.hpass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.hpass.dto.MypageDTO;
import com.hyundai.hpass.dto.TodayStoreVisitResDto;
import com.hyundai.hpass.service.MypageService;
import com.hyundai.hpass.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Log4j2
public class MypageController {
	private final MypageService mypageService;
	/**
	 작성자: 김은서
	 처리 내용: 유저정보 - 마이페이지
	 */
	@GetMapping("")
	public ResponseEntity<MypageDTO> userInfo(
		Authentication authentication
	) {
		MypageDTO res = mypageService.userInfo(Integer.parseInt(authentication.getName()));
		return ResponseEntity.ok().body(res);
	}
}
