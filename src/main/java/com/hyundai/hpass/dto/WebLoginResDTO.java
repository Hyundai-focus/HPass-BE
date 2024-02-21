package com.hyundai.hpass.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebLoginResDTO {
    private String accessToken;
    private String refreshToken;
    private String name;
    private String role;
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
