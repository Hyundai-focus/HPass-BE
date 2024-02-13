package com.hyundai.hpass.controller;

import com.hyundai.hpass.dto.LoginReqDto;
import com.hyundai.hpass.dto.LoginResDto;
import com.hyundai.hpass.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@Log4j2
public class MemberController {
    @Autowired
    private MemberService userService;
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResDto> login(
            @RequestParam String email,
            @RequestParam String memberName
    ) {
        LoginResDto loginResDto = userService.login(email, memberName);
        log.debug("이메일로 회원가입 - email: " + email + " memberName: " + memberName);
        log.debug("tokenDto : " + loginResDto.getAccessToken());
        return new ResponseEntity<>(loginResDto, HttpStatus.ACCEPTED);
    }
}
