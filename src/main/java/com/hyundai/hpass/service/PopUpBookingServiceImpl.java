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
	        // ���� �߰��� ���� �� ���� ���� ��� ����
	        bookingMapper.lockBookingRow(dto);

	        // ���� ���� ���� Ȯ��
	        boolean isAvailable = bookingMapper.isBookingAvailable(dto);

	        if (!isAvailable) {
	            // �̹� ����� ��� �ѹ�
	            return false;
	        }

	        // ���� �߰�
	        boolean insertFlag = bookingMapper.insertBooking(dto) == 1;
	        return insertFlag;
	    } catch (Exception e) {
	        // ���� ó��
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
