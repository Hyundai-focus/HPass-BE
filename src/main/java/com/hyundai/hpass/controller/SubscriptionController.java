package com.hyundai.hpass.controller;

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
    @Value("${bootpay.api-key}")
    private String bootKey;
    
    
    //이후 수정할 예정
    @GetMapping
    public ResponseEntity<String> getPaymentToken(
            Authentication authentication
    ) {
        Bootpay bootpay = new Bootpay("65cc38c400be04001d1f294d","GmJRQaPf1FtOmjWQHK38jM4azJxzR3ev/mwtmhp9jlc=");
        UserToken userToken = new UserToken();
        userToken.userId = authentication.getName();

        try {
            bootpay.getAccessToken();
            HashMap<String, Object> res = bootpay.getUserToken(userToken);
            if(res.get("error_code") == null) { //success
                System.out.println("getUserToken success: " + res);
            } else {
                System.out.println("getUserToken false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<> ("", HttpStatus.ACCEPTED);
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
}


