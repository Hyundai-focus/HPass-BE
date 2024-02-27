package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Subscription;
import com.hyundai.hpass.dto.AdminMainDTO;
import com.hyundai.hpass.dto.SubsAdminDTO;

public interface SubscriptionService {
    void addSubscriber(String payment, int memberNo);
    Subscription getSubscribeInfo(int memberNo);
    void stopSubscription(int memberNo, String lastDate);
    SubsAdminDTO[] getSubsAdminDTO();
    AdminMainDTO[] getAdminMainDTO();
}
