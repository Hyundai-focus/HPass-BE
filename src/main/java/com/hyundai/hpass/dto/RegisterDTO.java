package com.hyundai.hpass.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String userId;
    private String password;
    private String name;
    private int roleNo;
}
