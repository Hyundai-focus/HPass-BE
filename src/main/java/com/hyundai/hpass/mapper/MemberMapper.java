package com.hyundai.hpass.mapper;

import com.hyundai.hpass.domain.Member;
// 작성자 : 최현서
public interface MemberMapper {
    Member findByEmail(String email);
    Member findByMemberNo(int memberNo);
    void saveMember(Member member);
    void updateRefreshToken(Member member);
    void register(Member member);
}
