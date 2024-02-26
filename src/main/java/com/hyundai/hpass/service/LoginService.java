package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.RegisterDTO;
import com.hyundai.hpass.dto.WebLoginReqDTO;
import com.hyundai.hpass.dto.WebLoginResDTO;

public interface LoginService {
    WebLoginResDTO login(WebLoginReqDTO loginReqDTO);
    String signUp(RegisterDTO registerDTO);
}
