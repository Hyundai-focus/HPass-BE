package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.LoginReqDto;
import com.hyundai.hpass.dto.LoginResDto;
import org.springframework.security.core.userdetails.UserDetails;


public interface MemberService {
    public LoginResDto login(String email, String memberName);
}
