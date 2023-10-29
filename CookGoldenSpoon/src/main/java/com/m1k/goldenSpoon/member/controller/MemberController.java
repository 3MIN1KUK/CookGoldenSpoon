package com.m1k.goldenSpoon.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService service;
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "member/signup";
	}
	
	@PostMapping("login")
	public String login(String memberId, String memberPw) {
		
		Member loginMember = service.login(memberId, memberPw);
		
		return "redirect:/";
	} 
	
	@PostMapping("signup")
	public int signup(Member signupMember) {
		
		int resutl = service.signup(signupMember);
		
		return "redirect:/";
	}
	
}
