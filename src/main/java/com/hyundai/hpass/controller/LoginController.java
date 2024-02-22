package com.hyundai.hpass.controller;

import com.hyundai.hpass.dto.RegisterDTO;
import com.hyundai.hpass.dto.WebLoginReqDTO;
import com.hyundai.hpass.dto.WebLoginResDTO;
import com.hyundai.hpass.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<WebLoginResDTO> webLogin(@RequestBody WebLoginReqDTO loginReqDto){
        WebLoginResDTO loginResDto = loginService.login(loginReqDto);
        log.debug("LoginController 웹 로그인 - loginDto: " + loginReqDto.toString());
        if (loginResDto == null) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(loginResDto, HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<String> webSignUp(@RequestBody RegisterDTO registerDTO) {
        String registerResponse = loginService.signUp(registerDTO);
        return new ResponseEntity<>(registerResponse, HttpStatus.ACCEPTED);
    }
}
