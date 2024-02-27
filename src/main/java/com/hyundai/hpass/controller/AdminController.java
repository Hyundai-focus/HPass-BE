package com.hyundai.hpass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.dto.PageDTO;
import com.hyundai.hpass.dto.PopUpBookingDTO;
import com.hyundai.hpass.dto.ProductHistoryDTO;
import com.hyundai.hpass.service.PopUpBookingService;
import com.hyundai.hpass.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private PopUpBookingService popUpBookingService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}

	@GetMapping
	public String mainPage(Model model) {
		List<PopUpBookingDTO> list = popUpBookingService.getCountBooking();
		model.addAttribute("list", list);
		
		List<PopUpBookingDTO> count = popUpBookingService.getCountBooking();
		model.addAttribute("count", count);
		
		List<ProductHistoryDTO> collect1 = productService.getCountProduct();
		model.addAttribute("collect1", collect1);
		
		List<ProductHistoryDTO> collect2 = productService.getSumProduct();
		model.addAttribute("collect2", collect2);
		
		return "admin/main";
	}

	/**
	작성자: 황수연
	처리 내용: 팝업스토어 관리
	*/
	@GetMapping("/popup")
	public void popUpList(Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("list request");

		List<PopUpBookingDTO> list = popUpBookingService.getBookingsList(cri); // 사용자가 요청한 번호에 맞는 게시물 목록 요청
		int total = popUpBookingService.getTotalCnt(cri); // 전체 게시물 개수
		
		List<PopUpBookingDTO> popupList = popUpBookingService.getAllPopups();
	    model.addAttribute("popupList", popupList);

		model.addAttribute("list", list); // 목록 정보 넘기기
		model.addAttribute("pageDTO", new PageDTO(cri, total)); // 페이지 관련 정보 넘기기
	}
	
	/**
	작성자: 황수연
	처리 내용: 신제품 신청 현황
	*/
	@GetMapping("/registerproduct")
	public void registerProductList(Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("list request");
		
		List<ProductHistoryDTO> register = productService.getProductsList(cri);
		int total = productService.getTotalCnt(cri); // 전체 게시물 개수

		model.addAttribute("register", register); // 목록 정보 넘기기
		model.addAttribute("pageDTO", new PageDTO(cri, total)); // 페이지 관련 정보 넘기기
	}
	
	/**
	작성자: 황수연
	처리 내용: 신제품 수령 현황
	*/
	@GetMapping("/getproduct")
	public void getProductList(Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("list request");
		
		List<ProductHistoryDTO> get = productService.getReceiveList(cri);
		int total = productService.getTotalCnt(cri); // 전체 게시물 개수

		model.addAttribute("get", get); // 목록 정보 넘기기
		model.addAttribute("pageDTO", new PageDTO(cri, total)); // 페이지 관련 정보 넘기기
	}

}