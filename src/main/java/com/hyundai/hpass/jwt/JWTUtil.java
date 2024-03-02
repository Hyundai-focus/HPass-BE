package com.hyundai.hpass.jwt;

import com.hyundai.hpass.dto.LoginResDTO;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.util.Base64;
import java.util.Date;

@Log4j2
@Component
@RequiredArgsConstructor
public class JWTUtil implements InitializingBean  {
    @Value("${jwt.access-validity}")
    private long accessValidity;
    @Value("${jwt.refresh-validity}")
    private long refreshValidity;
    @Value("${jwt.secret}")
    private String jwtSecret;

    private final JWTService jwtService;

    @Override
    public void afterPropertiesSet() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }
    //jwt 생성
    public LoginResDTO createLoginResDto(String id, String memberName) {
        String accessToken = createJwtToken(id, accessValidity);
        String refreshToken = createJwtToken(id, refreshValidity);

        return LoginResDTO.builder()
                .accessToken("Bearer " + accessToken)
                .refreshToken("Bearer " + refreshToken)
                .isMember(true)
                .isSubscribed(false)
                .memberNo(Integer.parseInt(id))
                .memberName(memberName)
                .build();
    }

    public String createJwtToken(String id, long validity) {
        Date now = new Date();
        Claims claims = Jwts.claims()
                .setSubject(id);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setExpiration(new Date(now.getTime() + validity))
                .compact();
    }

    public Claims isTokenValidate(String token) throws Exception {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            throw new Exception();
        }
    }

    public Authentication getAuthentication(String accessToken, Claims claims) {
        UserDetails userDetails = jwtService.loadUserByUserNo(claims.getSubject());
        log.debug("AuthTokenGenerator getAuthentication() userDetails : " + userDetails);

        return new UsernamePasswordAuthenticationToken(
                userDetails, accessToken, userDetails.getAuthorities());
    }

}
