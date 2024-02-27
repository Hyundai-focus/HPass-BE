package com.hyundai.hpass.mapper;


import com.hyundai.hpass.domain.Subscription;
import com.hyundai.hpass.dto.AdminMainDTO;
import com.hyundai.hpass.dto.SubsAdminDTO;
import org.apache.ibatis.annotations.Param;

public interface SubscriptionMapper {
    void addSubscriber(@Param("payment") String payment, @Param("memberNo") int memberNo);
    Subscription findByMemberNo(int memberNo);
    void removeByMemberNo(@Param("memberNo") int memberNo, @Param("lastDate") String lastDate);
    SubsAdminDTO[] getSubsAdminDTO();
    AdminMainDTO[] getAdminMainDTO();
}
