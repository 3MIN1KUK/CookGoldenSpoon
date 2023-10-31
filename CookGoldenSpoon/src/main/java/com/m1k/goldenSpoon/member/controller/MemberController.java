package com.m1k.goldenSpoon.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
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
	public String login(String memberId, String memberPw, Model model) {
		
		Member loginMember = service.login(memberId, memberPw);
		
		if (loginMember == null) {
			return "redirect:/member/login";
		}
		
		model.addAttribute("loginMember", loginMember);
		
		return "redirect:/";
	} 
	
	
	@PostMapping("signup")
	public String signup(Member signupMember) {
		
		int resutl = service.signup(signupMember);
		
		return "redirect:/";
	}
	
}
