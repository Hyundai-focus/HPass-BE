package com.hyundai.hpass.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminSubsDTO {
    private String subsMonth;
    private int subsAddCnt;
    private int subsStopCnt;
    private int cumulativeCnt;
    private int subsLeft;
}
