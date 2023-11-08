package com.m1k.goldenSpoon.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("memberDetail")
	public String memberDetail() {
		return "admin/member_detail";
	}
	
	 
	@GetMapping("commentResult")
	public String commentResult() {
		return "admin/comment_result";
	}
	
	@GetMapping("boardResult")
	public String boardResult() {
		return "admin/board_result";
	}
	
	@GetMapping("recipeResult")
	public String recipeResult() {
		return "admin/recipe_result";
	}
	
	@GetMapping("basic")
	public String basic() {
		return "basicForm";
	}
	
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
			model.addAttribute("map", map);
			
		}
		return "admin/member_search";
	}
	
	
}
