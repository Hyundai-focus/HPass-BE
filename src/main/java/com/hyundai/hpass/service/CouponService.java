package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.CouponHistoryDTO;
import java.util.List;

public interface CouponService {
    List<CouponHistoryDTO> getAllCoupon(int memberNo);
    boolean issueCoupon(long memberNo, long couponNo);
}
