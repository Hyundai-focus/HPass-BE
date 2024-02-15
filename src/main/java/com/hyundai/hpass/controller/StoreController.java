package com.hyundai.hpass.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.hpass.dto.TodayStoreVisitResDto;
import com.hyundai.hpass.service.TodayStoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@Log4j2
public class StoreController {

	private final TodayStoreService todayStoreService;

	@GetMapping("/visit")
	public ResponseEntity<List<TodayStoreVisitResDto>> todayStoreList(
		Authentication authentication
	) {
		List<TodayStoreVisitResDto> res = todayStoreService.todayStoreVisitList((long)Integer.parseInt(authentication.getName()));;
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/visit/{storeNo}")
	public ResponseEntity<Boolean> userVisitStore(
		Authentication authentication,
		@PathVariable Long storeNo
	) {
		Boolean res = todayStoreService.userVisitStore((long)Integer.parseInt(authentication.getName()), storeNo);
		return ResponseEntity.ok().body(res);
	}
}
