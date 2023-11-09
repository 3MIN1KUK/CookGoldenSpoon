package com.m1k.goldenSpoon.member.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// 로그인
	@PostMapping("login")
	public String login(String memberId, String memberPw, Model model, RedirectAttributes ra) {
		  
		Member loginMember = service.login(memberId, memberPw);
		
		if (loginMember == null) {
			ra.addFlashAttribute("message", "로그인 실패");
			return "redirect:/member/login";
		}
		
		model.addAttribute("loginMember", loginMember);
		return "redirect:/";
	} 
	
	// 회원가입
	@PostMapping("signup")
	public String signup(Member signupMember, RedirectAttributes ra) {
		
		int result = service.signup(signupMember);
		
		if (result == 0) {
			ra.addFlashAttribute("message", "회원가입이 실패하였습니다");
			return "redirect:signup";
		}
		
		ra.addFlashAttribute("message", "회원가입이 성공하였습니다");
		return "redirect:/";
	}
	
	// 이메일 중복 체크
	@GetMapping("checkEmail")
	@ResponseBody
	public int checkEmail(String email) {
		
		return service.checkEamil(email);
	}
	
	// 닉네임 중복 체크
	@GetMapping("checkNickname")
	@ResponseBody
	public int checkNickname(String nickname) {
		return service.checkNickname(nickname);
	}
	
	
	
	
	// 아이디 찾기
	@PostMapping("findId")
	public String findId(String memberEmail, Model model,
			RedirectAttributes ra) {
		
		String memberId = service.findId(memberEmail);
		model.addAttribute("memberId",memberId);
		
		if (memberId == null) {
			ra.addFlashAttribute("message", "검색 결과가 없습니다");
			return "redirect:findPw";
		}
		
		return "member/find_id_result";
	}
	
	@GetMapping("changePw")
	public String changePw(Member searchMember, Model model,
			RedirectAttributes ra) {
		
		String memberNo = service.findMember(searchMember);
		model.addAttribute("memberNo", memberNo);
		if (memberNo == null) {
			ra.addFlashAttribute("message", "검색 결과가 없습니다");
			return "redirect:findPw";
		}
		
		return "member/change_pw";
	}
	
	@PostMapping("changePw")
	public String changePw(int memberNo, String memberPw, RedirectAttributes ra) {
		
		int result = service.changePw(memberNo,memberPw);
		
		ra.addFlashAttribute("message", "비밀번호 변경 성공");
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("logout")
	public String logout(
			SessionStatus status, 
			RedirectAttributes ra) {
		
		status.setComplete();
		ra.addFlashAttribute("message", "로그아웃 하였습니다");
		
		return "redirect:/";
	}
	
	
}
