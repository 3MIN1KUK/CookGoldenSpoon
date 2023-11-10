package com.m1k.goldenSpoon.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.m1k.goldenSpoon.admin.model.service.AdminService;
import com.m1k.goldenSpoon.member.model.dto.Member;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService service;

	
	@GetMapping("detailInquiry")
	public String detailInquiry() {
		return "admin/detailed_inquiry";
	}
	
	 
	@GetMapping("commentResult")
	public String commentResult() {
		return "admin/comment_result";
	}
	
	
	
	@GetMapping("basic")
	public String basic() {
		return "basicForm";
	}
	
	
	// 회원 상세 조회
	@PostMapping("memberDetail")
	public String memberDetail(Member searchMember, int cp, Model model) {
		int memberNo = searchMember.getMemberNo();
		
		Member myPageMember = service.memberDetail(memberNo);
		model.addAttribute("searchMember", searchMember);
		model.addAttribute("myPageMember", myPageMember);
		model.addAttribute("cp",cp);
		
		return "admin/member_detail";
	}
	
	// 회원 검색
	@GetMapping("memberSearch")
	public String memberSearch(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp,
			@RequestParam Map<String, Object> paramMap,
			Model model) {
		
		Member searchMember = new Member();
		
		// 검색이 아닌 경우 (일반 목록조회)
		if (paramMap.get("memberId") == null && 
			paramMap.get("memberEmail") == null &&
			paramMap.get("memberNickname") == null) {
			Map<String, Object> map = service.selectMember(cp);
			model.addAttribute("map",map); 
		}
		else { // 검색인 경우
			
			Map<String, Object> map = service.searchMember(paramMap, cp);
			model.addAttribute("memberId", paramMap.get("memberId"));
			model.addAttribute("memberEmail", paramMap.get("memberEmail"));
			model.addAttribute("memberNickname", paramMap.get("memberNickname"));
			model.addAttribute("map", map);
			
		}
		return "admin/member_search";
	}
	
	// 관리자 권한 변경
	@PutMapping("changeAuthority")
	@ResponseBody
	public int changeAuthority(@RequestBody Member member) {
		
		return service.changeAuthority(member);
	}
	
	// 회원 레시피 조회
	@GetMapping("recipeResult")
	public String recipeResult(int memberNo, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		Map<String, Object> map = service.recipeResult(memberNo, cp);
		model.addAttribute("memberNo",memberNo);
		model.addAttribute("map", map);
		
		return "admin/recipe_result";
	}
	
	// 회원 게시판 조회
	@GetMapping("boardResult")
	public String boardResult() {
		return "admin/board_result";
	}
	
}
