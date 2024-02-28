package com.hyundai.hpass.dto;

import lombok.Data;

@Data
public class CouponHistoryDTO {
    private long couponHistoryNo;
    private long couponNo;
    private long memberNo;
    private boolean couponIsUsed;
    private String couponUsedDt;
}
