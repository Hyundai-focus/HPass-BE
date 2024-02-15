package com.hyundai.hpass.controller;

import com.hyundai.hpass.dto.LoginResDto;
import com.hyundai.hpass.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
@Log4j2
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    @PostMapping
    public ResponseEntity<String> addSubscriber(
            @RequestParam String payment,
            Authentication authentication
    ) {
        log.debug("결제 방법 : " + payment);
        log.debug("회원아이디 : " + authentication.getName());
        subscriptionService.addSubscriber(payment, Integer.parseInt(authentication.getName()));;
        return new ResponseEntity<> ("success", HttpStatus.ACCEPTED);
    }
}


