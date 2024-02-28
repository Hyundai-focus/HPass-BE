package com.hyundai.hpass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.hyundai.hpass.service.CouponService;
import com.hyundai.hpass.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequiredArgsConstructor
public class AdminController {

	private final PopUpBookingService popUpBookingService;
	private final ProductService productService;
	private final SubscriptionService subscriptionService;
	private final CouponService couponService;
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}

	/**
	 작성자: 황수연, 최현서
	 처리 내용: 관리자 메인
	 */
	@GetMapping
	public String mainPage(Model model) {
		List<PopUpBookingDTO> list = popUpBookingService.getCountBooking();
		model.addAttribute("list", list);
		List<PopUpBookingDTO> count = popUpBookingService.getCountBooking();
		model.addAttribute("count", count);
		model.addAttribute("adminMainList", subscriptionService.getAdminMainDTO());
		model.addAttribute("unusedCouponNum", couponService.getUnusedCouponNum());
		model.addAttribute("adminMainSubsList", subscriptionService.getAdminMainSubsDTO());
		return "admin/main";
	}
	/**
	 작성자: 최현서
	 처리 내용: 구독 관리
	 */
	@GetMapping("/subscription")
	public String subscriptionList(Model model) {
		model.addAttribute("adminSubsList", subscriptionService.getAdminSubsDTO());
		Criteria cri = new Criteria(1, 10);
		model.addAttribute("subscriptionList", subscriptionService.getAllSubscription(cri));
		int total = subscriptionService.getTotalCnt();
		model.addAttribute("total", total);
		model.addAttribute("pageDTO", new PageDTO(cri, total));
		return "admin/subscription";
	}

	@PostMapping("/subscription/list")
	public ResponseEntity<Map<String, Object>> getSubscriptionList(@ModelAttribute("cri") Criteria cri) {
		Map<String, Object> result = new HashMap<>();
		result.put("subscriptionList", subscriptionService.getAllSubscription(cri));
		int total = subscriptionService.getTotalCnt();
		result.put("total", String.valueOf(total));
		result.put("pageDTO", new PageDTO(cri, total));
		return ResponseEntity.ok(result);
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
	@GetMapping("/product/apply")
	public String registerProductList(Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("list request");
		
		List<ProductHistoryDTO> register = productService.getProductsList(cri);
		int total = productService.getTotalCnt(cri); // 전체 게시물 개수

		model.addAttribute("register", register); // 목록 정보 넘기기
		model.addAttribute("pageDTO", new PageDTO(cri, total)); // 페이지 관련 정보 넘기기
		return "admin/registerproduct";
	}
	
	/**
	작성자: 황수연
	처리 내용: 신제품 수령 현황
	*/
	@GetMapping("/product/get")
	public String getProductList(Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("list request");
		
		List<ProductHistoryDTO> get = productService.getReceiveList(cri);
		int total = productService.getTotalCnt(cri); // 전체 게시물 개수

		model.addAttribute("get", get); // 목록 정보 넘기기
		model.addAttribute("pageDTO", new PageDTO(cri, total)); // 페이지 관련 정보 넘기기
		return "admin/getproduct";
	}

}