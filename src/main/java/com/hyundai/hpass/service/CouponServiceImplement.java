package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.TestDTO;
import com.hyundai.hpass.mapper.CouponMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class CouponServiceImplement implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public TestDTO selectTest() {
        log.info("selectTest() called");
        return couponMapper.selectTest();
    }
}
