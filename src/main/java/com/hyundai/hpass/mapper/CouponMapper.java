package com.hyundai.hpass.mapper;

import com.hyundai.hpass.dto.CouponHistoryDTO;
import com.hyundai.hpass.dto.MyCouponDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapper {
    List<MyCouponDTO> selectMyAllCoupon(int memberNo);
    CouponHistoryDTO selectMyCoupon(
            @Param("memberNo") long memberNo,
            @Param("couponNo") long couponNo);

    void insertCoupon(
            @Param("couponNo") long couponNo,
            @Param("memberNo") long memberNo);

    int getUnusedCouponNum();
    
    public List<CouponHistoryDTO> getAllIssuedCoupons();
    
    public List<CouponHistoryDTO> getAllUsedCoupons();
}
