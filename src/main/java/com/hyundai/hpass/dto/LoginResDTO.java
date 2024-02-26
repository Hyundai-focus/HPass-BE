package com.hyundai.hpass.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDTO {
    private Boolean isMember;
    private String memberName;
    private int memberNo;
    private Boolean isSubscribed;
    private String accessToken;
    private String refreshToken;
}