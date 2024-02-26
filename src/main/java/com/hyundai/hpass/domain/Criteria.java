package com.hyundai.hpass.domain;

import lombok.Data;

/**
작성자: 황수연
처리 내용: 관리자 페이지네이션
*/
@Data
public class Criteria {
	private int page; // 페이지 번호
	private int amount; // 한 페이지당 몇 개의 게시물을 보여줄 것인가
	
	public Criteria() {
		this(1,10); // 디폴트 생성자: 1페이지 당 10개
	}

	public Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
}
