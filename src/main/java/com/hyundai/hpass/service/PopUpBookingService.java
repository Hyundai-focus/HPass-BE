package com.hyundai.hpass.service;

import java.util.List;

import com.hyundai.hpass.dto.PopUpBookingDTO;

/**
�ۼ���: Ȳ����
ó�� ����: �˾������ ���� ������ ���
*/
public interface PopUpBookingService {
	public List<PopUpBookingDTO> getBookingsWithinPopupPeriod(int popupNo, String popupStartDt, String popupEndDt);
    
    public List<PopUpBookingDTO> getBookingByDateTime(int popupNo, String bookingDt, String bookingTime);
    
	public boolean insertBooking(PopUpBookingDTO dto);
}
