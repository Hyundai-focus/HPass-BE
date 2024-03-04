package com.hyundai.hpass.dto;

import lombok.Data;

public class AdminPopupBookingDTO {
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
     팝업스토어 이름
     */
    private String popupName;

    /**
     팝업스토어 시작일
     */
    private String popupStartDt;

    /**
     팝업스토어 종료일
     */
    private String popupEndDt;

    /**
     팝업스토어 장소
     */
    private String popupLocation;

    /**
     팝업스토어 이미지
     */
    private String popupImg;

    /**
     팝업스토어별 예약 내역 횟수
     */
    private int bookingCount;

    public String getBookingNo() {
        return String.format("RESRV%06d", bookingNo);
    }

    public String getMemberNo() {
        return String.format("MEMBER%06d", memberNo);
    }

    public String getPopupNo() {
        return String.format("POPUP%06d", popupNo);
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public String getBookingDt() {
        return bookingDt;
    }

    public String getPopupName() {
        return popupName;
    }

    public String getPopupStartDt() {
        return popupStartDt;
    }

    public String getPopupEndDt() {
        return popupEndDt;
    }

    public String getPopupLocation() {
        return popupLocation;
    }

    public String getPopupImg() {
        return popupImg;
    }

    public int getBookingCount() {
        return bookingCount;
    }
}
