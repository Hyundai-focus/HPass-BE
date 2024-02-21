package com.hyundai.hpass.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private int subsNo;
    private int memberNo;
    private LocalDateTime subsStartDt;
    private String payment;
    private String lastDate;
}
