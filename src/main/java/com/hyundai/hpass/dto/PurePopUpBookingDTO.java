package com.hyundai.hpass.dto;

import lombok.Data;

@Data
public class PurePopUpBookingDTO {
    private long bookingNo;
    private long memberNo;
    private long popUpNo;
    private String bookingTime;
    private String bookingDate;
}
