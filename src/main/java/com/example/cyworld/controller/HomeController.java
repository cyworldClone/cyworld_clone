package com.example.cyworld.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cyworld.config.PrincipalDetails;
import com.example.cyworld.config.UserInfo;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal PrincipalDetails userInfo, Model model) {
    	log.info("userInfo:{}",userInfo);
    	model.addAttribute("loginUser", userInfo);
        return "index";
    }
    
    @GetMapping("admin")
    public String adminHome(@AuthenticationPrincipal UserInfo userInfo, Model model) {
    	model.addAttribute("adminUser", userInfo);
    	return "admin/index";
    }
    
   
}
