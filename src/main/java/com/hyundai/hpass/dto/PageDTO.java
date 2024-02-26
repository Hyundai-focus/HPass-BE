package com.hyundai.hpass.dto;

import com.hyundai.hpass.domain.Criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.total=total;
		this.cri=cri;
		
		this.endPage=(int)(Math.ceil(cri.getPage()/10.0))*10;
		this.startPage=this.endPage-9;
		
		int realEnd=(int)(Math.ceil((total/1.0)/cri.getAmount()));
		if(realEnd<this.endPage) {
			this.endPage=realEnd;
		}
		this.prev=this.startPage>1; // 2,3,4 부터는 이전 페이지 클릭 가능
		this.next=this.endPage<realEnd;
	}
}
