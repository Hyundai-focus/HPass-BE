package com.hyundai.hpass.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WebLoginReqDTO {
    private String userId;
    private String password;
}
