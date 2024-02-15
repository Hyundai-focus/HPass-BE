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
�ۼ���: Ȳ����
ó�� ����: ���� ���� API ó��
*/
@Log4j2
@RestController
@RequestMapping("/popup")
public class PopUpController {
	
	@Autowired
	PopUpBookingService bookingService;
	
	/**
	 ó�� ����: ���� ������ ��� API
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
	 ó�� ����: �˾������ �Ⱓ �� ��� ���� ���� ���� ��ȸ API
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
	 ó�� ����: Ư�� ��¥�� �ش��ϴ� ���� ���� ���� ��ȸ API
	*/
	
	/**
	 ó�� ����: Ư�� ��¥�� �ð��� �ش��ϴ� ���� ���� ���� ��ȸ API
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
