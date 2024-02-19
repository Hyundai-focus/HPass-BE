package com.hyundai.hpass.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.hpass.dto.MypageDTO;
import com.hyundai.hpass.mapper.MypageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
@RequiredArgsConstructor
public class MypageServiceImplement implements MypageService {
	private final MypageMapper mypageMapper;
	@Override
	public MypageDTO userInfo(long memberNo) {
		String name = mypageMapper.selectMemberName(memberNo);
		int subsNum = mypageMapper.selectSubs(memberNo);
		if(subsNum == 0)
			return MypageDTO.builder()
				.name(name)
				.status(false).build();
		else return MypageDTO.builder()
				.name(name)
				.status(true).build();
	}
}
