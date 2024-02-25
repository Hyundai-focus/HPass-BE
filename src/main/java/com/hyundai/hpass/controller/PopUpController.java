package com.hyundai.hpass.controller;

import java.util.List;

import com.hyundai.hpass.dto.PopUpStoreDTO;
import com.hyundai.hpass.service.PopUpStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class PopUpController {
	
	@Autowired
	PopUpBookingService bookingService;

	@Autowired
	PopUpStoreService popUpStoreService;
	
	/**
	 처리 내용: 예약 데이터 등록 API
	*/
	// http://localhost:8080/popup/booking
	@PostMapping("popup/booking")
	public ResponseEntity<String> insert(Authentication authentication, @RequestBody PopUpBookingDTO dto) {
		try {
			dto.setMemberNo(Integer.parseInt(authentication.getName()));
			boolean result = bookingService.insertBooking(dto);
			if (result) {
				return new ResponseEntity<>("Inserted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Failed to insert: The time slot is already booked.", HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to insert: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 처리 내용: 팝업스토어 기간 내 모든 예약 정보 내역 조회 API
	*/
	// http://localhost:8080/popup/booking/list?popupNo=1&popupStartDt=2024-02-10&popupEndDt=2024-02-27
	@GetMapping("popup/booking/list")
	public ResponseEntity<List<PopUpBookingDTO>> getBookingList(
			Authentication authentication,
			@RequestParam("popupNo") int popupNo,
	        @RequestParam("popupStartDt") String popupStartDt,
	        @RequestParam("popupEndDt") String popupEndDt) {
		List<PopUpBookingDTO> bookingList = bookingService.getBookingsWithinPopupPeriod(popupNo, popupStartDt, popupEndDt);
		log.info("getBookingList");
		return new ResponseEntity<>(bookingList, HttpStatus.OK);
	}
	
	/**
	 처리 내용: 특정 날짜와 시간에 해당하는 예약 정보 내역 조회 API
	*/
	// http://localhost:8080/popup/booking/detail/list?popupNo=1&bookingDt=2024-02-13&bookingTime=19:00
	@GetMapping("popup/booking/detail/list")
	public ResponseEntity<List<PopUpBookingDTO>> getBookingDetailList(
			Authentication authentication,
			@RequestParam("popupNo") int popupNo,
	        @RequestParam("bookingDt") String bookingDt,
	        @RequestParam("bookingTime") String bookingTime) {
		List<PopUpBookingDTO> detailList = bookingService.getBookingByDateTime(popupNo, bookingDt, bookingTime);
		return new ResponseEntity<>(detailList, HttpStatus.OK);
	}
	
	/**
	 처리 내용: 나의 예약 조회 API
	*/
	// http://localhost:8080/popup/booking/mylist
	@GetMapping("popup/booking/mylist")
	public ResponseEntity<List<PopUpBookingDTO>> getMyBooking(Authentication authentication) {
		List<PopUpBookingDTO> myList = bookingService.getMyBooking(Integer.parseInt(authentication.getName()));
		return new ResponseEntity<>(myList, HttpStatus.OK);
	}
	
	
	/**
	 처리 내용: 나의 예약 삭제 API
	*/
	//
	@DeleteMapping("popup/booking/{bookingNo}")
	public ResponseEntity<String> deleteBooking(Authentication authentication, @PathVariable int bookingNo) {
		boolean deleted = bookingService.deleteBooking(bookingNo);
		
        if (deleted) {
            return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete booking", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@GetMapping("popup/list")
	public ResponseEntity<List<PopUpStoreDTO>> getAllPopUpStoreList() {
		List<PopUpStoreDTO> stores = popUpStoreService.getAllPopUpStore();

		return new ResponseEntity<>(stores, HttpStatus.OK);
	}
}
