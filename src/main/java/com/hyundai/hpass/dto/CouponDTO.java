package com.hyundai.hpass.dto;

import lombok.Data;

@Data
public class CouponDTO {
    private long couponHistoryNo;
    private long couponNo;
    private long memberNo;
    private String couponIsUsed;
    private String couponUsedDt;
}
