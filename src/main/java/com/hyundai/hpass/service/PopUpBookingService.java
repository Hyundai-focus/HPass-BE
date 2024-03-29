package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.dto.AdminPopupBookingDTO;
import com.hyundai.hpass.dto.PopUpBookingDTO;

import java.util.List;

/**
작성자: 황수연
처리 내용: 팝업스토어 예약 데이터 등록
*/
public interface PopUpBookingService {
	public List<PopUpBookingDTO> getBookingsWithinPopupPeriod(int popupNo, String popupStartDt, String popupEndDt);
	
	public List<PopUpBookingDTO> getBookingsList(Criteria cri);
    List<AdminPopupBookingDTO> getAllBooking();
    public List<PopUpBookingDTO> getBookingByDateTime(int popupNo, String bookingDt, String bookingTime);
    
	public boolean insertBooking(PopUpBookingDTO dto);
	
	public List<PopUpBookingDTO> getMyBooking(int memberNo);
	
	public boolean deleteBooking(int bookingNo);

	// 작성자 : 김기훈
	PopUpBookingDTO checkPopUpBooking(long memberNo, long popUpNo) throws Exception;
	
	// 전체 게시물 개수
	public int getTotalCnt(Criteria cri);
	
	public List<PopUpBookingDTO> getAllPopups();
	
	public List<PopUpBookingDTO> getCountBooking();
}
