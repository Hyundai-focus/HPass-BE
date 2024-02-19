package com.hyundai.hpass.mapper;

public interface MypageMapper {
	String selectMemberName(Long memberNo);

	Integer selectSubs(Long memberNo);
}
