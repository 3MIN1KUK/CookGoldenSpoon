package com.m1k.goldenSpoon.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.myPage.model.service.MyPageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("my_page")
@RequiredArgsConstructor
public class MyPageController {

	
	private final MyPageService service;
	
	/*
	 * @PostMapping("teacher") public String teacher() { return
	 * "my_page/teacher/cookingclass_teacher"; }
	 */
	
	/** 프로필 이미지 수정
	 * @param profileImg
	 * @return
	 */
	@PostMapping("profile")
	public String profile(@RequestParam("profileImage") MultipartFile profileImg) {
		return null;
	}
	
	
	@GetMapping("bookmark")
	public String bookmark() {
		
		return "my_page/bookmark/bookmark";
	}
	
	
	@GetMapping("comments")
	public String comments() {
		
		return "my_page/comments/comments";
	}
	
	
	@GetMapping("manage")
	public String manage() {
		
		return "my_page/manage/manage";
	}
	
	
	@GetMapping("star")
	public String star() {
		
		return "my_page/star/star";
	}
	
	
	
	
}
