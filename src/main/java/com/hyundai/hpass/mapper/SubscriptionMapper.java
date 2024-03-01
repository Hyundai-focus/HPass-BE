package com.hyundai.hpass.mapper;


import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.domain.Subscription;
import com.hyundai.hpass.dto.AdminMainDTO;
import com.hyundai.hpass.dto.AdminMainSubsDTO;
import com.hyundai.hpass.dto.AdminSubsDTO;
import com.hyundai.hpass.dto.SubscriptionDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubscriptionMapper {
    void addSubscriber(@Param("payment") String payment, @Param("memberNo") int memberNo);
    Subscription findByMemberNo(int memberNo);
    void removeByMemberNo(@Param("memberNo") int memberNo, @Param("lastDate") String lastDate);
    void extendByMemberNo(int memberNo);
    List<AdminMainDTO> getAdminMainDTO();
    List<AdminMainSubsDTO> getAdminMainSubsDTO();
    List<AdminSubsDTO> getAdminSubsDTO();
    List<SubscriptionDTO> getAllSubscription(Criteria cri);
    int getTotalCnt();
}
