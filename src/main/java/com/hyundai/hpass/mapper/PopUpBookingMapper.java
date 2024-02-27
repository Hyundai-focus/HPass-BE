package com.hyundai.hpass.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.hyundai.hpass.dto.PopUpBookingDTO;

/**
 * 작성자: 황수연 처리 내용: 팝업스토어 예약 데이터 등록
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
	
	public List<PopUpBookingDTO> getMyBooking(int memberNo);
	
	public int deleteMyBookingList(int bookingNo);

	PopUpBookingDTO getPopUpBooking(
			@Param("memberNo") long memberNo,
			@Param("popUpNo") long popUpNo,
			@Param("bookingDate") String bookingDate);

}
