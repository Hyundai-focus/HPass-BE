package com.hyundai.hpass.dto;

import lombok.Getter;

@Getter
public class IssueCouponDTO {
    private long couponHistoryNo;
    private long couponNo;
    private long memberNo;
    private String couponIsUsed;
    private String couponUsedDt;
    private String couponBrand;
    private String couponContent;
}
