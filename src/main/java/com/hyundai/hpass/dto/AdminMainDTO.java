package com.hyundai.hpass.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdminMainDTO {
    private String mainDt;
    private int couponUseCnt;
    private int allVisitCnt;
}
