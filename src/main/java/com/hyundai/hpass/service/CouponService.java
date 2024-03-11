package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Coupon;
import com.hyundai.hpass.dto.CouponDTO;
import com.hyundai.hpass.dto.IssueCouponDTO;
import com.hyundai.hpass.dto.MyCouponDTO;

import java.util.List;

public interface CouponService {
    // 작성자 : 김기훈
    List<MyCouponDTO> getMyAllCoupon(int memberNo);
    // 작성자 : 김기훈
    boolean issueCoupon(long memberNo, long couponNo);
    // 작성자 : 김기훈
    boolean issueCouponByStore(long memberNo, long storeNo);
    int getUnusedCouponNum();
    
    // 작성자: 황수연
    public List<IssueCouponDTO> getAllIssuedCoupons();
    
    // 작성자: 황수연
    public List<CouponDTO> getAllUsedCoupons();

    // 작성자 : 김기훈
    boolean isExistCoupon(long memberNo, long couponNo);
    // 작성자 : 김기훈
    boolean useCoupon(long memberNo, long couponNo);
    List<Coupon> getAllCoupon();

    int insertCoupon(Coupon coupon);
    boolean deleteCoupon(int couponNo);
}
