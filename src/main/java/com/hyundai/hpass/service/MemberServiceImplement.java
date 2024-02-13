package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Member;
import com.hyundai.hpass.dto.LoginResDto;
import com.hyundai.hpass.mapper.MemberMapper;
import com.hyundai.hpass.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImplement implements MemberService {

    private final MemberMapper memberMapper;
    private final JWTUtil jwtUtil;
    @Value("${jwt.access-validity}")
    private long accessValidity;


    @Override
    public LoginResDto login(String email, String memberName) {
        Member findMember = memberMapper.findByEmail(email);
        if (findMember != null) {
            return getUpdatedToken(findMember);
        }
        return joinByEmail(email, memberName);
    }

    public LoginResDto joinByEmail(String email, String memberName) {
        Member member = Member.builder()
                .email(email)
                .memberName(memberName)
                .build();
        memberMapper.saveMember(member);
        int memberNo = member.getMemberNo();
        log.debug("join memberNo"+memberNo);
        LoginResDto loginResDto = jwtUtil.createLoginResDto(String.valueOf(memberNo));
        member.updateRefreshToken(loginResDto.getRefreshToken());
        memberMapper.updateRefreshToken(member);

        log.trace("joinByEmail savedMember" + member);
        return loginResDto;
    }

    private LoginResDto getUpdatedToken(Member member) {
        String newAccessToken = updateAccessToken(member);
        String refreshToken = member.getRefreshToken();

        return LoginResDto.builder()
                .accessToken("Bearer " + newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }
    private String updateAccessToken(Member member) {
        String memberId = String.valueOf(member.getMemberNo());
        return jwtUtil.createJwtToken(memberId, accessValidity);
    }

}
