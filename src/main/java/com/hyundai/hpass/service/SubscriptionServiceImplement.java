package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Subscription;
import com.hyundai.hpass.dto.AdminMainDTO;
import com.hyundai.hpass.dto.SubsAdminDTO;
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

    @Override
    public Subscription getSubscribeInfo(int memberNo) {
        return subscriptionMapper.findByMemberNo(memberNo);
    }

    @Override
    public void stopSubscription(int memberNo, String lastDate) {
        subscriptionMapper.removeByMemberNo(memberNo, lastDate);
    }

    @Override
    public SubsAdminDTO[] getSubsAdminDTO() {
        return subscriptionMapper.getSubsAdminDTO();
    }
    @Override
    public AdminMainDTO[] getAdminMainDTO() { return subscriptionMapper.getAdminMainDTO();}
}
