package com.hyundai.hpass.controller;

import com.hyundai.hpass.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final SubscriptionService subscriptionService;
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }
    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("adminMainList", subscriptionService.getAdminMainDTO());
        return "admin/main";
    }

    @GetMapping("/member")
    public String member(Model model) {
        model.addAttribute("subsAdminList", subscriptionService.getSubsAdminDTO());
        return "admin/member";
    }
}