package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.CouponDTO;
import com.hyundai.hpass.dto.MyCouponDTO;

import java.util.List;

public interface CouponService {
    List<MyCouponDTO> getMyAllCoupon(int memberNo);
    boolean issueCoupon(long memberNo, long couponNo);
    boolean issueCouponByStore(long memberNo, long storeNo);
    int getUnusedCouponNum();
    
    // 작성자: 황수연
    public List<CouponDTO> getAllIssuedCoupons();
    
    // 작성자: 황수연
    public List<CouponDTO> getAllUsedCoupons();

    boolean isExistCoupon(long memberNo, long couponNo);
    boolean useCoupon(long memberNo, long couponNo);
}
