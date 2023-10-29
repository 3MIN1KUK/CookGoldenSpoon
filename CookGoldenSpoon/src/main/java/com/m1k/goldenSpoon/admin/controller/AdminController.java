package com.m1k.goldenSpoon.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

	@GetMapping("memberSearch")
	public String memberSearch() {
		return "admin/member_search";
	}  
	
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
	
}
