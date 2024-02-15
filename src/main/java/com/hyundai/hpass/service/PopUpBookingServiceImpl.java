package com.hyundai.hpass.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.hpass.dto.PopUpBookingDTO;
import com.hyundai.hpass.mapper.PopUpBookingMapper;

@Service
public class PopUpBookingServiceImpl implements PopUpBookingService {

	@Autowired
	PopUpBookingMapper bookingMapper;

	@Override
	@Transactional
	public boolean insertBooking(PopUpBookingDTO dto) {
		try {
	        // 예약 추가를 위해 행 레벨 선점 잠금 설정
	        bookingMapper.lockBookingRow(dto);

	        // 예약 가능 여부 확인
	        boolean isAvailable = bookingMapper.isBookingAvailable(dto);

	        if (!isAvailable) {
	            // 이미 예약된 경우 롤백
	            return false;
	        }

	        // 예약 추가
	        boolean insertFlag = bookingMapper.insertBooking(dto) == 1;
	        return insertFlag;
	    } catch (Exception e) {
	        // 예외 처리
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<PopUpBookingDTO> getBookingsWithinPopupPeriod(int popupNo, String popupStartDt, String popupEndDt) {
		return bookingMapper.getBookingsWithinPopupPeriod(popupNo, popupStartDt, popupEndDt);
	}

	@Override
	public List<PopUpBookingDTO> getBookingByDateTime(int popupNo, String bookingDt, String bookingTime) {
		return bookingMapper.getBookingByDateTime(popupNo, bookingDt, bookingTime);
	}

}
