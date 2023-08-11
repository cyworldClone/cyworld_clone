package com.example.cyworld.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cyworld.config.PrincipalDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class minihonpController {

	@GetMapping("myhomp")
	public String minihonP(@AuthenticationPrincipal PrincipalDetails userInfo, Model model) {
		model.addAttribute("loginUser", userInfo);
		return "minihomP/minihonP_main";
	}
}
