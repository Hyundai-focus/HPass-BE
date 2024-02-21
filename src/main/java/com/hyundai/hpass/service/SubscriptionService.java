package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Subscription;

public interface SubscriptionService {
    void addSubscriber(String payment, int memberNo);
    Subscription getSubscribeInfo(int memberNo);
    void stopSubscription(int memberNo, String lastDate);
}
