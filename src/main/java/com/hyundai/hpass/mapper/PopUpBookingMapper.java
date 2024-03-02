package com.hyundai.hpass.mapper;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.dto.PopUpBookingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 작성자: 황수연 처리 내용: 팝업스토어 예약 데이터 등록
 */
@Repository
@Mapper
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
	
	public boolean isDuplicateBooking(PopUpBookingDTO dto);
	
	public Integer lockBookingRow(PopUpBookingDTO dto);
	
	public List<PopUpBookingDTO> getMyBooking(
			@Param("member") int member,
			@Param("date") String date);
	
	public int deleteMyBookingList(int bookingNo);
	
	public List<PopUpBookingDTO> list(Criteria cri);
	
	public int totalCnt(Criteria cri);

	public List<PopUpBookingDTO> getAllPopups();
	
	public List<PopUpBookingDTO> getCountBooking();

	PopUpBookingDTO getPopUpBooking(
			@Param("memberNo") long memberNo,
			@Param("popUpNo") long popUpNo,
			@Param("bookingDate") String bookingDate);

}
