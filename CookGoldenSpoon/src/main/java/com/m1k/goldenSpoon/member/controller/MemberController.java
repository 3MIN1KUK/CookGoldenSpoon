package com.m1k.goldenSpoon.member.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
	
	@GetMapping("findId")
	public String findId() {
		return "member/find_id";
	}
	
	@GetMapping("findPw")
	public String findPw() {
		return "member/find_pw";
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
	
	
	@PostMapping("findId")
	public String findId(String memberEmail, Model model) {
		
		String memberId = service.findId(memberEmail);
		model.addAttribute("memberId",memberId);
		
		return "member/find_id_result";
	}
	
	@GetMapping("changePw")
	public String changePw(Member searchMember, Model model) {
		
		int memberNo = service.findMember(searchMember);
		model.addAttribute("memberNo", memberNo);
		
		return "member/change_pw";
	}
	
	@PostMapping("changePw")
	public String changePw(int memberNo, String memberPw) {
		
		int result = service.changePw(memberNo,memberPw);
		
		if (result == 0) {
			return null;
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping("logout")
	public String logout(
			SessionStatus status) {
		
		status.setComplete();
		
		return "redirect:/";
	}
	
}
