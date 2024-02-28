package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.CouponHistoryDTO;
import com.hyundai.hpass.dto.MyCouponDTO;

import java.util.List;

public interface CouponService {
    List<MyCouponDTO> getMyAllCoupon(int memberNo);
    boolean issueCoupon(long memberNo, long couponNo);
    int getUnusedCouponNum();
    
    public List<CouponHistoryDTO> getAllIssuedCoupons();
    public List<CouponHistoryDTO> getAllUsedCoupons();

    boolean isExistCoupon(long memberNo, long couponNo);
}
