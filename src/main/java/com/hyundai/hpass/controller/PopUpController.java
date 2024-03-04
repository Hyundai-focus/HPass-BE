package com.hyundai.hpass.controller;

import com.hyundai.hpass.dto.PopUpBookingDTO;
import com.hyundai.hpass.dto.PopUpStoreDTO;
import com.hyundai.hpass.service.PopUpBookingService;
import com.hyundai.hpass.service.PopUpStoreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
	 작성자: 황수연
	 처리 내용: 예약 데이터 등록 API
	*/
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
	 작성자: 황수연
	 처리 내용: 팝업스토어 기간 내 모든 예약 정보 내역 조회 API
	*/
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
	 작성자: 황수연 
	 처리 내용: 특정 날짜와 시간에 해당하는 예약 정보 내역 조회 API
	*/
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
	 작성자: 황수연
	 처리 내용: 나의 예약 조회 API
	*/
	@GetMapping("popup/booking/mylist")
	public ResponseEntity<List<PopUpBookingDTO>> getMyBooking(Authentication authentication) {
		List<PopUpBookingDTO> myList = bookingService.getMyBooking(Integer.parseInt(authentication.getName()));
		return new ResponseEntity<>(myList, HttpStatus.OK);
	}
	
	
	/**
	 작성자: 황수연
	 처리 내용: 나의 예약 삭제 API
	*/
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

	@GetMapping("popup/booking/{popUpNo}")
	public ResponseEntity<PopUpBookingDTO> checkPopUpBooking(
			@PathVariable long popUpNo,
			Authentication authentication
	) {
		PopUpBookingDTO booking = bookingService.checkPopUpBooking(Long.parseLong(authentication.getName()), popUpNo);


		return new ResponseEntity<>(booking, HttpStatus.OK);

	}
}
