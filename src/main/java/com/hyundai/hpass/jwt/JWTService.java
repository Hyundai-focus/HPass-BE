package com.hyundai.hpass.jwt;

import com.hyundai.hpass.domain.Member;
import com.hyundai.hpass.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
public class JWTService {
    private final MemberMapper memberMapper;
    public UserDetails loadUserByUserNo(String id) throws UsernameNotFoundException {
        Member member = memberMapper.findByMemberNo(Integer.parseInt(id));
        log.debug("loadUserByUsername() => member : " + member);
        return createUserDetails(member);
    }
    private UserDetails createUserDetails(Member member) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(member.getRole().toString()));

        return new User(
                String.valueOf(member.getMemberNo()),
                String.valueOf(member.getMemberNo()),
                grantedAuthorities);
    }
}
