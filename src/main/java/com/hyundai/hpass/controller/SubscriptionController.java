package com.hyundai.hpass.controller;

import com.hyundai.hpass.domain.Subscription;
import com.hyundai.hpass.service.SubscriptionService;
import kr.co.bootpay.Bootpay;
import kr.co.bootpay.model.request.UserToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
@Log4j2
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    @Value("${bootpay.rest-api-key}")
    private String bootKey;
    @Value("${bootpay.private-key}")
    private String privateKey;
    
    
    //이후 수정할 예정
    @GetMapping
    public ResponseEntity<Object> getPaymentToken(
            Authentication authentication
    ) {
        Bootpay bootpay = new Bootpay(bootKey,privateKey);
        UserToken userToken = new UserToken();
        userToken.userId = authentication.getName();
        HashMap<String, Object> res = null;
        try {
            bootpay.getAccessToken();
            res = bootpay.getUserToken(userToken);
            if(res.get("error_code") == null) { //success
                log.debug("getUserToken success: " + res);
            } else {
                log.debug("getUserToken false: " + res);
            }
        } catch (Exception e) {
            log.error("Error Occurs!!", e);
        }
        if (res == null) {
            return new ResponseEntity<>(" ", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(res.getOrDefault("user_token",""), HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<String> addSubscriber(
            Authentication authentication,
            @RequestParam String payment

    ) {
        log.debug("결제 방법 : " + payment);
        log.debug("회원아이디 : " + authentication.getName());
        subscriptionService.addSubscriber(payment, Integer.parseInt(authentication.getName()));;
        return new ResponseEntity<> ("success", HttpStatus.ACCEPTED);
    }

    @GetMapping("/info")
    public ResponseEntity<Subscription> getSubscribeInfo(
            Authentication authentication
    ) {
        log.debug("회원아이디 : " + authentication.getName());
        return new ResponseEntity<> (subscriptionService.getSubscribeInfo(Integer.parseInt(authentication.getName())), HttpStatus.ACCEPTED);
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopSubscription(
            Authentication authentication,
            @RequestParam String lastDate
    ) {
        log.debug("회원아이디 : " + authentication.getName());
        log.debug("구독 만료 기한: " + lastDate);
        subscriptionService.stopSubscription(Integer.parseInt(authentication.getName()), lastDate);
        return new ResponseEntity<> ("success", HttpStatus.ACCEPTED);
    }
    @PostMapping("/more")
    public ResponseEntity<String> extendSubscription(
            Authentication authentication
    ) {
        log.debug("회원아이디 : " + authentication.getName());
        subscriptionService.extendSubscription(Integer.parseInt(authentication.getName()));
        return new ResponseEntity<> ("success", HttpStatus.ACCEPTED);
    }
}


