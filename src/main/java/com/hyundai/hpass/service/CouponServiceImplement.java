package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.CouponHistoryDTO;
import com.hyundai.hpass.dto.MyCouponDTO;
import com.hyundai.hpass.mapper.CouponMapper;
import com.hyundai.hpass.websocket.HpassWebSocketHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CouponServiceImplement implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public List<MyCouponDTO> getMyAllCoupon(int memberNo) {
        return couponMapper.selectMyAllCoupon(memberNo);
    }

    @Override
    public boolean issueCoupon(long memberNo, long couponNo) {
        CouponHistoryDTO coupon = couponMapper.selectMyCoupon(memberNo, couponNo);

        if (coupon == null) {
            couponMapper.insertCoupon(couponNo, memberNo);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getUnusedCouponNum() {
        return couponMapper.getUnusedCouponNum();
    }
}
