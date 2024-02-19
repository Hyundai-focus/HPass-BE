package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.LoginResDTO;


public interface MemberService {
    public LoginResDTO verifyMember(int memberNo);
    public LoginResDTO login(String email, String memberName);
}
