package com.hyundai.hpass.dto;

import lombok.Data;

@Data
public class PopUpBookingDTO {
	/**
	 예약 아이디
	 */
	private int bookingNo;
	
	/**
	 멤버 아이디
	 */
	private int memberNo;
	
	/**
	 팝업스토어 아이디
	 */
	private int popupNo;
	
	/**
	 팝업스토어 예약시간
	 */
	private String bookingTime;
	
	/**
	 팝업스토어 예약일자
	 */
	private String bookingDt;
	
	/**
	 팝업스토어 시작일
	 */
	private String popupStartDt;
	
	/**
	 팝업스토어 종료일
	 */
	private String popupEndDt;
}
