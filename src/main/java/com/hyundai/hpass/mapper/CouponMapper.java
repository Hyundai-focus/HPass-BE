package com.hyundai.hpass.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hyundai.hpass.dto.CouponDTO;
import com.hyundai.hpass.dto.CouponHistoryDTO;
import com.hyundai.hpass.dto.MyCouponDTO;

public interface CouponMapper {
    List<MyCouponDTO> selectMyAllCoupon(int memberNo);
    CouponHistoryDTO selectMyCoupon(
            @Param("memberNo") long memberNo,
            @Param("couponNo") long couponNo);

    void insertCoupon(
            @Param("couponNo") long couponNo,
            @Param("memberNo") long memberNo);

    int getUnusedCouponNum();
    
    public List<CouponDTO> getAllIssuedCoupons();
    
    public List<CouponDTO> getAllUsedCoupons();
}
