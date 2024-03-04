package com.hyundai.hpass.dto;

import lombok.Getter;

@Getter
public class AdminRegisterProductDTO {
    private int productHistoryNo;
    private int memberNo;
    private int productNo;
    private String productHistoryDt;
    private String productStatus;
    private String productName;
    private String receiveDt;
}
