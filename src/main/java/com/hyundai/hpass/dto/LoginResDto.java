package com.hyundai.hpass.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDto {

    private String accessToken;

    private String refreshToken;
}