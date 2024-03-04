package com.hyundai.hpass.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Coupon {
    private int couponNo;
    private String couponBrand;
    private String couponContent;
    private String couponStartDt;
    private String couponEndDt;
}
