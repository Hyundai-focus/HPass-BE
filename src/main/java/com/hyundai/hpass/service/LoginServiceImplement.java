package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Member;
import com.hyundai.hpass.domain.enumType.Role;
import com.hyundai.hpass.dto.LoginResDTO;
import com.hyundai.hpass.dto.RegisterDTO;
import com.hyundai.hpass.dto.WebLoginReqDTO;
import com.hyundai.hpass.dto.WebLoginResDTO;
import com.hyundai.hpass.jwt.JWTUtil;
import com.hyundai.hpass.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoginServiceImplement implements LoginService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    @Value("${jwt.access-validity}")
    private long accessValidity;
    @Override
    public WebLoginResDTO login(WebLoginReqDTO loginReqDTO) {
        Member findMember = memberMapper.findByEmail(loginReqDTO.getUserId());
        if (findMember == null || !passwordEncoder.matches(loginReqDTO.getPassword(),findMember.getEncodedPassword())) return null;
        return getUpdatedToken(findMember);
    }

    private WebLoginResDTO getUpdatedToken(Member member) {
        String newAccessToken = updateAccessToken(member);
        String refreshToken = member.getRefreshToken();
        return WebLoginResDTO.builder()
                .accessToken("Bearer " + newAccessToken)
                .refreshToken(refreshToken)
                .name(member.getMemberName())
                .role(member.getRole().toString())
                .build();
    }
    private String updateAccessToken(Member member) {
        String memberId = String.valueOf(member.getMemberNo());
        return jwtUtil.createJwtToken(memberId, accessValidity);
    }

    public String signUp(RegisterDTO registerDTO) {
        Member findMember = memberMapper.findByEmail(registerDTO.getUserId());
        if (findMember != null) return "fail";
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
        Member member = Member.builder()
                .email(registerDTO.getUserId())
                .memberName(registerDTO.getName())
                .encodedPassword(encodedPassword)
                .roleNo(registerDTO.getRoleNo())
                .build();
        memberMapper.register(member);
        int memberNo = member.getMemberNo();
        log.debug("register memberNo"+memberNo);
        LoginResDTO loginResDto = jwtUtil.createLoginResDto(String.valueOf(memberNo), member.getMemberName());
        member.updateRefreshToken(loginResDto.getRefreshToken());
        memberMapper.updateRefreshToken(member);
        return "success";
    }
}
