package com.hyundai.hpass.service;

import com.hyundai.hpass.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class SubscriptionServiceImplement implements SubscriptionService {
    private final SubscriptionMapper subscriptionMapper;
    @Override
    @Transactional
    public void addSubscriber(String payment, int memberNo) {
        subscriptionMapper.addSubscriber(payment, memberNo);
    }
}
