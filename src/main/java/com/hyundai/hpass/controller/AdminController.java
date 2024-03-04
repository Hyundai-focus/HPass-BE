package com.hyundai.hpass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hyundai.hpass.dto.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.hpass.domain.Criteria;
import com.hyundai.hpass.service.CouponService;
import com.hyundai.hpass.service.PopUpBookingService;
import com.hyundai.hpass.service.ProductService;
import com.hyundai.hpass.service.SubscriptionService;
import com.hyundai.hpass.service.TodayStoreService;

import lombok.RequiredArgsConstructor;
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
	private final TodayStoreService todayStoreService;
	
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

		List<AdminPopupBookingDTO> list = popUpBookingService.getAllBooking();
		
		List<PopUpBookingDTO> popupList = popUpBookingService.getAllPopups();
		
	    model.addAttribute("popupList", popupList);
		model.addAttribute("list", list);
	}
	
	/**
	 작성자: 황수연
	 처리 내용: 예약 삭제 API
	*/
	@DeleteMapping("popup/booking/{bookingNo}")
	public ResponseEntity<String> deleteBooking(Authentication authentication, @PathVariable int bookingNo) {
		boolean deleted = popUpBookingService.deleteBooking(bookingNo);
		
        if (deleted) {
            return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete booking", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	/**
	작성자: 황수연
	처리 내용: 신제품 신청 현황
	*/
	@GetMapping("/product/apply")
	public String registerProductList(Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("list request");
		
		List<ProductHistoryDTO> register = productService.getProductsList(cri);

		model.addAttribute("register", register);
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

		model.addAttribute("get", get);
		return "admin/getproduct";
	}
	
	/**
	작성자: 황수연
	처리 내용: 쿠폰 발급 현황
	*/
	@GetMapping("/coupon/issue")
	public String issueList(Model model) {
		log.info("issue request");
		
		List<CouponDTO> issue = couponService.getAllIssuedCoupons();

		model.addAttribute("issue", issue);
		return "admin/issuecoupon";
	}
	
	/**
	작성자: 황수연
	처리 내용: 쿠폰 사용 현황
	*/
	@GetMapping("/coupon/use")
	public String useList(Model model) {
		log.info("use request");
		
		List<CouponDTO> use = couponService.getAllUsedCoupons();

		model.addAttribute("use", use);
		return "admin/usecoupon";
	}
	
	/**
	작성자: 황수연
	처리 내용: 매장 방문 현황
	*/
	@GetMapping("/visit")
	public String visitList(Model model) {
		log.info("visit request");
		
		List<TodayVisitStore> today = todayStoreService.getTodayStore();
		List<TodayVisitStore> visit = todayStoreService.getVisitStore();

		model.addAttribute("today", today);
		model.addAttribute("visit", visit);
		return "admin/visit";
	}

}