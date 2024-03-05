package com.hyundai.hpass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hyundai.hpass.domain.Coupon;
import com.hyundai.hpass.domain.Product;
import com.hyundai.hpass.dto.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	 작성자: 최현서
	 처리 내용: 신제품 목록
	 */
	@GetMapping("/product/list")
	public String productList(Model model) {
		log.info("list request");
		List<Product> list = productService.getProductList();
		model.addAttribute("list", list);
		return "admin/productlist";
	}
	/**
	 작성자: 최현서
	 처리 내용: 신제품 등록 API
	 */
	@PostMapping("/product/insert")
	@ResponseBody
	public ResponseEntity<String> insertProduct(@RequestBody Product productRequest) {
		log.info("admin insert product request:" + productRequest.toString());
		int result = productService.insertProduct(productRequest);
		if (result == 1) return ResponseEntity.ok("success");
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert product");
	}
	/**
	 작성자: 최현서
	 처리 내용: 신제품 삭제 API
	 */
	@DeleteMapping("/product/{productNo}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productNo) {
		boolean deleted = productService.deleteProduct(productNo);

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
	public String registerProductList(Model model) {
		log.info("list request");

		List<AdminRegisterProductDTO> register = productService.getRegisterList();
		model.addAttribute("register", register);
		return "admin/registerproduct";
	}

	/**
	 작성자: 최현서
	 처리 내용: 쿠폰 목록
	 */
	@GetMapping("/coupon/list")
	public String couponList(Model model) {
		log.info("coupon list request");
		List<Coupon> list = couponService.getAllCoupon();
		model.addAttribute("list", list);
		return "admin/couponlist";
	}
	/**
	 작성자: 최현서
	 처리 내용: 쿠폰 등록 API
	 */
	@PostMapping("/coupon/insert")
	@ResponseBody
	public ResponseEntity<String> insertCoupon(@RequestBody Coupon couponRequest) {
		log.info("admin insert coupon request:" + couponRequest.toString());
		int result = couponService.insertCoupon(couponRequest);
		if (result == 1) return ResponseEntity.ok("success");
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert coupon");
	}
	/**
	 작성자: 최현서
	 처리 내용: 쿠폰 삭제 API
	 */
	@DeleteMapping("/coupon/{couponNo}")
	public ResponseEntity<String> deleteCoupon(@PathVariable int couponNo) {
		boolean deleted = couponService.deleteCoupon(couponNo);

		if (deleted) {
			return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to delete booking", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 작성자: 황수연
	 처리 내용: 쿠폰 발급 현황
	 */
	@GetMapping("/coupon/issue")
	public String issueList(Model model) {
		log.info("issue request");

		List<IssueCouponDTO> issue = couponService.getAllIssuedCoupons();

		model.addAttribute("issue", issue);
		return "admin/issuecoupon";
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