package com.hyundai.hpass.dto;

import lombok.Data;

@Data
public class PopUpBookingDTO {
	/**
	 ���� ���̵�
	 */
	private int bookingNo;
	
	/**
	 ��� ���̵�
	 */
	private int memberNo;
	
	/**
	 �˾������ ���̵�
	 */
	private int popupNo;
	
	/**
	 �˾������ ����ð�
	 */
	private String bookingTime;
	
	/**
	 �˾������ ��������
	 */
	private String bookingDt;
	
	/**
	 �˾������ ������
	 */
	private String popupStartDt;
	
	/**
	 �˾������ ������
	 */
	private String popupEndDt;
}
