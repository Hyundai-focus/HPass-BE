package com.hyundai.hpass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.hpass.dto.PopUpBookingDTO;
import com.hyundai.hpass.service.PopUpBookingService;

import lombok.extern.log4j.Log4j2;


/**
작성자: 황수연
처리 내용: 예약 관련 API 처리
*/
@Log4j2
@RestController
@RequestMapping("/popup")
public class PopUpController {
	
	@Autowired
	PopUpBookingService bookingService;
	
	/**
	 처리 내용: 예약 데이터 등록 API
	*/
	// http://localhost:8080/popup/booking
	@PostMapping("/booking")
	public ResponseEntity<String> insert(@RequestBody PopUpBookingDTO dto) {
		try {
			bookingService.insertBooking(dto);
			return new ResponseEntity<>("Inserted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to insert: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 처리 내용: 팝업스토어 기간 내 모든 예약 정보 내역 조회 API
	*/
	// http://localhost:8080/popup/booking/list?popupNo=1&popupStartDt=2024-02-05&popupEndDt=2024-02-14
	@GetMapping("/booking/list")
	public ResponseEntity<List<PopUpBookingDTO>> getBookingList(
			@RequestParam("popupNo") int popupNo,
	        @RequestParam("popupStartDt") String popupStartDt,
	        @RequestParam("popupEndDt") String popupEndDt) {
		List<PopUpBookingDTO> bookingList = bookingService.getBookingsWithinPopupPeriod(popupNo, popupStartDt, popupEndDt);
		log.info("getBookingList");
		return new ResponseEntity<>(bookingList, HttpStatus.OK);
	}
	
	/**
	 처리 내용: 특정 날짜에 해당하는 예약 정보 내역 조회 API
	*/
	
	/**
	 처리 내용: 특정 날짜와 시간에 해당하는 예약 정보 내역 조회 API
	*/
	// http://localhost:8080/popup/booking/detail/list?popupNo=1&bookingDt=2024-02-13&bookingTime=19:00
	@GetMapping("/booking/detail/list")
	public ResponseEntity<List<PopUpBookingDTO>> getBookingDetailList(
			@RequestParam("popupNo") int popupNo,
	        @RequestParam("bookingDt") String bookingDt,
	        @RequestParam("bookingTime") String bookingTime) {
		List<PopUpBookingDTO> detailList = bookingService.getBookingByDateTime(popupNo, bookingDt, bookingTime);
		return new ResponseEntity<>(detailList, HttpStatus.OK);
	}
	
}
