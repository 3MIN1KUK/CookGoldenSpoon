package com.m1k.goldenSpoon.myPage.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.myPage.model.service.MyPageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("myPage")
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class MyPageController {

	
	private final MyPageService service;
	
	/*
	 * @PostMapping("teacher") public String teacher() { return
	 * "my_page/teacher/cookingclass_teacher"; }
	 */
	
	@GetMapping("/main")
	public String myPage(@SessionAttribute("loginMember") Member loginMember, Model model) {
		int memberNo = loginMember.getMemberNo();
		Member myPageMember = service.myPage(memberNo);
		model.addAttribute("myPageMember", myPageMember);
		return "my_page/myPage";
	}
	
	@GetMapping("like")
	public String myPageLike(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		int memberNo = loginMember.getMemberNo();
		
		Map<String, Object> map = service.myPageLike(memberNo, cp);
		model.addAttribute("map", map);
		return "my_page/like/myPageLike";
	}
	
	@GetMapping("bookmark")
	public String myPageBookmark(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		int memberNo = loginMember.getMemberNo();
		
		Map<String, Object> map = service.myPageBookmark(memberNo, cp);
		model.addAttribute("map", map);
		return "my_page/bookmark/mypageBookmark";
	}
	
	@GetMapping("board")
	public String myPageBoard(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		int memberNo = loginMember.getMemberNo();
		Map<String, Object> map = service.myPageBoard(memberNo, cp);
		model.addAttribute("map", map);
		
		return "my_page/board/myPageBoard";
	}
	
	@GetMapping("recipe")
	public String myPageRecipe(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		int memberNo = loginMember.getMemberNo();
		Map<String, Object> map = service.myPageRecipe(memberNo, cp);
		model.addAttribute("map", map);
		
		return "my_page/recipe/myPageRecipe";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 프로필 이미지 수정
	 * @param profileImg
	 * @return
	 */
	@PostMapping("profile")
	public String profile(@RequestParam("profileImage") MultipartFile profileImg) {
		return null;
	}
	
}
