package com.hyundai.hpass.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private int memberNo;
    private String memberName;
    private String email;
    private Date regDt;
    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
