package com.hyundai.hpass.vo;

import com.hyundai.hpass.dto.PopUpBookingDTO;
import lombok.Data;

import java.util.List;

@Data
public class PopUpBookingCheckVO {
    private long memberNo;
    private long popUpNo;
    private String bookingDate;
    private List<PopUpBookingDTO> result;
}
