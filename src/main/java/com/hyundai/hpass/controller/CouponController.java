package com.hyundai.hpass.controller;

import com.hyundai.hpass.dto.CouponHistoryDTO;
import com.hyundai.hpass.service.CouponService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
@Log4j2
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/list")
    public ResponseEntity<List<CouponHistoryDTO>> getAllCoupon(Authentication authentication) throws DataAccessException {
        List<CouponHistoryDTO> coupons = couponService.getAllCoupon(Integer.parseInt(authentication.getName()));

        if (coupons.isEmpty()) {
            return new ResponseEntity<>(coupons, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(coupons, HttpStatus.OK);
        }
    }

    @GetMapping("/issue/{couponNo}")
    public ResponseEntity<Boolean> issueCoupon(
            @PathVariable long couponNo,
            Authentication authentication
    ) {
        boolean isIssued = couponService.issueCoupon(Long.parseLong(authentication.getName()), couponNo);

        if (isIssued) {
            return new ResponseEntity<>(isIssued, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(isIssued, HttpStatus.BAD_REQUEST);
        }
    }
}
