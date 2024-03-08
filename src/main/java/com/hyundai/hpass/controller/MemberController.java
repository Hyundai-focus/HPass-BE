package com.hyundai.hpass.controller;

import com.hyundai.hpass.dto.LoginResDTO;
import com.hyundai.hpass.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// 작성자: 최현서
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    private final MemberService userService;

    @GetMapping
    public ResponseEntity<LoginResDTO> verifyMember(Authentication authentication){
        return new ResponseEntity<> (userService.verifyMember(Integer.parseInt(authentication.getName())), HttpStatus.ACCEPTED);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResDTO> login(
            @RequestParam String email,
            @RequestParam String memberName
    ) {
        LoginResDTO loginResDto = userService.login(email, memberName);
        log.debug("이메일로 회원가입 - email: " + email + " memberName: " + memberName);
        log.debug("tokenDto : " + loginResDto.getAccessToken());
        return new ResponseEntity<>(loginResDto, HttpStatus.ACCEPTED);
    }
}
