package com.hyundai.hpass.mapper;

import com.hyundai.hpass.domain.Coupon;
import com.hyundai.hpass.dto.CouponDTO;
import com.hyundai.hpass.dto.CouponHistoryDTO;
import com.hyundai.hpass.dto.IssueCouponDTO;
import com.hyundai.hpass.dto.MyCouponDTO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CouponMapper {
    // 작성자 : 김기훈
    List<MyCouponDTO> selectMyAllCoupon(int memberNo);

    // 작성자 : 김기훈
    CouponHistoryDTO selectMyCoupon(
            @Param("memberNo") long memberNo,
            @Param("couponNo") long couponNo);

    // 작성자 : 김기훈
    void insertCoupon(
            @Param("couponNo") long couponNo,
            @Param("memberNo") long memberNo);

    int getUnusedCouponNum();

    // 작성자: 황수연
    public List<IssueCouponDTO> getAllIssuedCoupons();

    // 작성자: 황수연
    public List<CouponDTO> getAllUsedCoupons();

    int useCoupon(@Param("couponNo") long couponNo,
                  @Param("memberNo") long memberNo,
                  @Param("date") String date);

    // 작성자 : 김기훈
    int insertCouponByStore(@Param("memberNo") long memberNo,
                            @Param("storeNo") long storeNo);

    // 작성자 : 김기훈
    void insertCouponBySubscription(@Param("memberNo") long memberNo);
    List<Coupon> getAllCoupon();
    int insertCouponAdmin(Coupon coupon);
    int deleteCoupon(int couponNo);

}