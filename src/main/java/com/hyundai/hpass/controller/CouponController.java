package com.hyundai.hpass.controller;

import com.hyundai.hpass.service.CouponService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
@Log4j2
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/list")
    public ResponseEntity<String> in() throws DataAccessException {
        return ResponseEntity.status(200).body(couponService.selectTest() + "");
    }
}
