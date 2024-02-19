package com.hyundai.hpass.domain;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private int subsNo;
    private int memberNo;
    private Date subsStartDt;
    private String payment;
}
