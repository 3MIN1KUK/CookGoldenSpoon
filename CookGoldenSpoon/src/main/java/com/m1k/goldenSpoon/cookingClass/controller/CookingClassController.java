package com.m1k.goldenSpoon.cookingClass.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.m1k.goldenSpoon.cookingClass.model.service.CookingClassService;

@Controller
@RequestMapping("cookingClass")
public class CookingClassController {
	
	@Autowired
	private CookingClassService service;
	
	// -------------------- 쿠킹클래스 메인 게시판 ---------------------
	@GetMapping("cookingClassBoard")
	public String cookingClassBoard () {
		return "/cooking_class/board/cookingClassBoard";
	}
	
	// -------------------- 쿠킹클래스 게시판 상세 조회 ---------------------
	@GetMapping("cookingClassMain")
	public String cookingClassMain() {
		return "/cooking_class/board/cookingClassMain";
	}
	
	// -------------------- 쿠킹클래스 게시판 조회 ---------------------
	@GetMapping("cookingClassRegistration")
	public String cookingClassRegistration() {
		return "/cooking_class/board/cookingClassRegistration";
	}
	
	// -------------------- 쿠킹클래스 커뮤니티 ---------------------
	@GetMapping("cookingClassCommuntry")
	public String cookingClassCommuntry() {
		return "/cooking_class/community/cookingClassCommuntry";
	}
	
	

	
	
	
	
	// 마이페이지 없어서 임시로 만들어둠 
	// 마이페이지 만들어지면 삭제 예정!
	@GetMapping("teacher")
	public String teacher() {
		return "/cooking_class/teacher/cookingclass_teacher";
	}
	
}
