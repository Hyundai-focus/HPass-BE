package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.CouponDTO;
import com.hyundai.hpass.dto.CouponHistoryDTO;
import com.hyundai.hpass.dto.MyCouponDTO;
import com.hyundai.hpass.mapper.CouponMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

	@Override
	public List<CouponDTO> getAllIssuedCoupons() {
		return couponMapper.getAllIssuedCoupons();
	}

	@Override
	public List<CouponDTO> getAllUsedCoupons() {
		return couponMapper.getAllUsedCoupons();
	}

    @Override
    public boolean isExistCoupon(long memberNo, long couponNo) {
        CouponHistoryDTO coupon = couponMapper.selectMyCoupon(memberNo, couponNo);
        return coupon != null;
    }

    @Override
    public boolean useCoupon(long memberNo, long couponNo) {
        LocalDate seoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedNow = seoulNow.format(formatter);

        return couponMapper.useCoupon(couponNo, memberNo, formattedNow) > 0;
    }
}
