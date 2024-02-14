package com.hyundai.hpass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public String todayStoreList(
		Authentication authentication
	) {
		todayStoreService.todayStoreVisitList((long)Integer.parseInt(authentication.getName()));;
		return null;
	}
}
