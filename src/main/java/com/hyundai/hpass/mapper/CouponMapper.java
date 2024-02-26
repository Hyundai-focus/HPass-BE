package com.hyundai.hpass.mapper;

import com.hyundai.hpass.dto.CouponHistoryDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface CouponMapper {
    List<CouponHistoryDTO> selectAllCoupon(int memberNo);
    CouponHistoryDTO selectMyCoupon(
            @Param("memberNo") long memberNo,
            @Param("couponNo") long couponNo);

    void insertCoupon(
            @Param("couponNo") long couponNo,
            @Param("memberNo") long memberNo);
}
