package com.hyundai.hpass.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hyundai.hpass.dto.PopUpBookingDTO;

/**
 * �ۼ���: Ȳ���� ó�� ����: �˾������ ���� ������ ���
 */
@Repository
public interface PopUpBookingMapper {

	public List<PopUpBookingDTO> getBookingsWithinPopupPeriod(
			@Param("popupNo") int popupNo,
			@Param("popupStartDt") String popupStartDt, 
			@Param("popupEndDt") String popupEndDt
	);

	public List<PopUpBookingDTO> getBookingByDateTime(
			@Param("popupNo") int popupNo, 
			@Param("bookingDt") String bookingDt,
			@Param("bookingTime") String bookingTime
	);

	public int insertBooking(PopUpBookingDTO dto);
	
	public boolean isBookingAvailable(PopUpBookingDTO dto);
	
	public Integer lockBookingRow(PopUpBookingDTO dto);
}
