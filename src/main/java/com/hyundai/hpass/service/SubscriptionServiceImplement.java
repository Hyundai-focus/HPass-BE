package com.hyundai.hpass.service;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.domain.Subscription;
import com.hyundai.hpass.dto.AdminMainDTO;
import com.hyundai.hpass.dto.AdminMainSubsDTO;
import com.hyundai.hpass.dto.AdminSubsDTO;
import com.hyundai.hpass.dto.SubscriptionDTO;
import com.hyundai.hpass.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<AdminMainDTO> getAdminMainDTO() {
        return subscriptionMapper.getAdminMainDTO();
    }

    @Override
    public List<AdminSubsDTO> getAdminSubsDTO() {
        return subscriptionMapper.getAdminSubsDTO();
    }

    @Override
    public List<AdminMainSubsDTO> getAdminMainSubsDTO() {
        return subscriptionMapper.getAdminMainSubsDTO();
    }

    @Override
    public List<SubscriptionDTO> getAllSubscription(Criteria criteria) {
        return subscriptionMapper.getAllSubscription(criteria);
    }

    @Override
    public int getTotalCnt() {
        return subscriptionMapper.getTotalCnt();
    }
}
