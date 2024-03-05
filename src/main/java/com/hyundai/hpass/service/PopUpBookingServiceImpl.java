package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.dto.AdminPopupBookingDTO;
import com.hyundai.hpass.dto.PopUpBookingDTO;
import com.hyundai.hpass.mapper.PopUpBookingMapper;
import com.hyundai.hpass.vo.PopUpBookingCheckVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
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
	        boolean isDuplicate = bookingMapper.isDuplicateBooking(dto);

	        // 예약이 불가능하거나 중복 예약인 경우 롤백
	        if (!isAvailable) {
	            // 예약 불가능한 경우 롤백
	            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	            return false;
	        }

	        if (isDuplicate) {
	            // 중복 예약인 경우 롤백
	            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	            return false;
	        }
	        
	        // 예약 추가
	        boolean insertFlag = bookingMapper.insertBooking(dto) == 1;
	        
	        // 삽입이 실패한 경우 롤백
	        if (!insertFlag) {
	            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        }
	        
	        return insertFlag;
	    } catch (Exception e) {
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

	@Override
	public List<PopUpBookingDTO> getMyBooking(int memberNo) {
		LocalDate seoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedNow = seoulNow.format(formatter);

		return bookingMapper.getMyBooking(memberNo, formattedNow);
	}

	@Override
	public boolean deleteBooking(int bookingNo) {
		int result = bookingMapper.deleteMyBookingList(bookingNo);
		log.info("Delete booking");
		return result == 1;
	}

	@Override
	public PopUpBookingDTO checkPopUpBooking(long memberNo, long popUpNo) throws Exception {
		LocalDate seoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedNow = seoulNow.format(formatter);

		PopUpBookingCheckVO popUpBookingCheckVO = new PopUpBookingCheckVO();
		popUpBookingCheckVO.setMemberNo(memberNo);
		popUpBookingCheckVO.setPopUpNo(popUpNo);
		popUpBookingCheckVO.setBookingDate(formattedNow);
		bookingMapper.getPopUpBooking(popUpBookingCheckVO);

		if (popUpBookingCheckVO.getResult().isEmpty()) {
			throw new Exception("");
		}
		return popUpBookingCheckVO.getResult().get(0);
	}

	@Override
	public List<PopUpBookingDTO> getBookingsList(Criteria cri) {
		return bookingMapper.list(cri);
	}

	@Override
	public List<AdminPopupBookingDTO> getAllBooking() {
		return bookingMapper.getAllBooking();
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return bookingMapper.totalCnt(cri);
	}

	@Override
	public List<PopUpBookingDTO> getAllPopups() {
		return bookingMapper.getAllPopups();
	}

	@Override
	public List<PopUpBookingDTO> getCountBooking() {
		return bookingMapper.getCountBooking();
	}

}
