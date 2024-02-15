package com.hyundai.hpass.service;

import java.util.List;

import com.hyundai.hpass.dto.PopUpBookingDTO;

/**
작성자: 황수연
처리 내용: 팝업스토어 예약 데이터 등록
*/
public interface PopUpBookingService {
	public List<PopUpBookingDTO> getBookingsWithinPopupPeriod(int popupNo, String popupStartDt, String popupEndDt);
    
    public List<PopUpBookingDTO> getBookingByDateTime(int popupNo, String bookingDt, String bookingTime);
    
	public boolean insertBooking(PopUpBookingDTO dto);
}
