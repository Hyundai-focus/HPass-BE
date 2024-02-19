package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Member;
import com.hyundai.hpass.domain.Subscription;
import com.hyundai.hpass.dto.LoginResDTO;
import com.hyundai.hpass.mapper.MemberMapper;
import com.hyundai.hpass.jwt.JWTUtil;
import com.hyundai.hpass.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImplement implements MemberService {

    private final MemberMapper memberMapper;
    private final SubscriptionMapper subscriptionMapper;
    private final JWTUtil jwtUtil;
    @Value("${jwt.access-validity}")
    private long accessValidity;


    @Override
    public LoginResDTO verifyMember(int memberNo) {
        boolean isMember = false;
        boolean isSubscribed = false;
        String newAccessToken = "";
        String refreshToken = "";
        String memberName = "";

        Member findMember = memberMapper.findByMemberNo(memberNo);

        if (findMember != null){
            isMember = true;
            memberName = findMember.getMemberName();
            newAccessToken = updateAccessToken(findMember);
            refreshToken = findMember.getRefreshToken();
            Subscription findSubscription = subscriptionMapper.findByMemberNo(memberNo);
            if (findSubscription != null) isSubscribed = true;
        }

        return LoginResDTO.builder()
                .accessToken("Bearer " + newAccessToken)
                .refreshToken(refreshToken)
                .isMember(isMember)
                .memberName(memberName)
                .isSubscribed(isSubscribed)
                .build();
    }

    @Override
    public LoginResDTO login(String email, String memberName) {
        Member findMember = memberMapper.findByEmail(email);
        if (findMember != null) {
            return getUpdatedToken(findMember);
        }
        return joinByEmail(email, memberName);
    }

    public LoginResDTO joinByEmail(String email, String memberName) {
        Member member = Member.builder()
                .email(email)
                .memberName(memberName)
                .build();
        memberMapper.saveMember(member);
        int memberNo = member.getMemberNo();
        log.debug("join memberNo"+memberNo);
        LoginResDTO loginResDto = jwtUtil.createLoginResDto(String.valueOf(memberNo));
        member.updateRefreshToken(loginResDto.getRefreshToken());
        memberMapper.updateRefreshToken(member);

        log.trace("joinByEmail savedMember" + member);
        return loginResDto;
    }

    private LoginResDTO getUpdatedToken(Member member) {
        String newAccessToken = updateAccessToken(member);
        String refreshToken = member.getRefreshToken();
        boolean isSubscribed = false;
        Subscription findSubscription = subscriptionMapper.findByMemberNo(member.getMemberNo());
        if (findSubscription != null) isSubscribed = true;

        return LoginResDTO.builder()
                .accessToken("Bearer " + newAccessToken)
                .refreshToken(refreshToken)
                .isMember(true)
                .memberName(member.getMemberName())
                .isSubscribed(isSubscribed)
                .build();
    }
    private String updateAccessToken(Member member) {
        String memberId = String.valueOf(member.getMemberNo());
        return jwtUtil.createJwtToken(memberId, accessValidity);
    }

}
