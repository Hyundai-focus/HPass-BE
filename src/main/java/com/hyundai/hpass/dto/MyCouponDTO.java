package com.hyundai.hpass.dto;

import lombok.Data;

@Data
public class MyCouponDTO {
    private long couponHistoryNo;
    private long couponNo;
    private long memberNo;
    private boolean couponIsUsed;
    private String couponBrand;
    private String couponContent;
    private String couponStartDate;
    private String couponEndDate;
    private String couponImage;
}
