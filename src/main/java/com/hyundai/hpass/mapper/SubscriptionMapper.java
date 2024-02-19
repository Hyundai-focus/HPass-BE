package com.hyundai.hpass.mapper;


import com.hyundai.hpass.domain.Subscription;
import org.apache.ibatis.annotations.Param;

public interface SubscriptionMapper {
    void addSubscriber(@Param("payment") String payment, @Param("memberNo") int memberNo);
    Subscription findByMemberNo(int memberNo);
}
