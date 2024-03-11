package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.domain.Subscription;
import com.hyundai.hpass.dto.AdminMainDTO;
import com.hyundai.hpass.dto.AdminMainSubsDTO;
import com.hyundai.hpass.dto.AdminSubsDTO;
import com.hyundai.hpass.dto.SubscriptionDTO;

import java.util.List;
// 작성자 : 최현서
public interface SubscriptionService {
    void addSubscriber(String payment, int memberNo);
    Subscription getSubscribeInfo(int memberNo);
    void stopSubscription(int memberNo, String lastDate);
    void extendSubscription(int memberNo);
    List<AdminMainDTO> getAdminMainDTO();
    List<AdminSubsDTO> getAdminSubsDTO();
    List<AdminMainSubsDTO> getAdminMainSubsDTO();
    List<SubscriptionDTO> getAllSubscription(Criteria criteria);
    int getTotalCnt();
}
