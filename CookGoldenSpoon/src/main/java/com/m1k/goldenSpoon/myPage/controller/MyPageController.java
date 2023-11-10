package com.m1k.goldenSpoon.myPage.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.myPage.model.dto.MyPagePwChange;
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
	
	@GetMapping("main")
	public String myPage(@SessionAttribute("loginMember") Member loginMember, Model model) {
		int memberNo = loginMember.getMemberNo();
		Member myPageMember = service.myPage(memberNo);
		model.addAttribute("myPageMember", myPageMember);
		return "my_page/myPage";
	}
	
//	@GetMapping("like")
//	public String myPageLike(@SessionAttribute("loginMember") Member loginMember, Model model,
//			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
//		int memberNo = loginMember.getMemberNo();
//		
//		Map<String, Object> map = service.myPageLike(memberNo, cp);
//		model.addAttribute("map", map);
//		return "my_page/like/myPageLike";
//	}
	
	@GetMapping("bookmark")
	public String myPageBookmark(@SessionAttribute("loginMember") Member loginMember, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		int memberNo = loginMember.getMemberNo();
		
		Map<String, Object> map = service.myPageBookmark(memberNo, cp);
		model.addAttribute("map", map);
		return "my_page/bookmark/myPageBookmark";
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
	
	@GetMapping("edit")
	public String myPageEdit(@SessionAttribute("loginMember") Member loginMember, Model model) {
		int memberNo = loginMember.getMemberNo();
		Member myPageMember = service.myPage(memberNo);
		model.addAttribute("myPageMember", myPageMember);
		return "my_page/edit/myPageEdit";
	}
	
	
	@PostMapping("edit")
	public String myPageEdit(Model model, @SessionAttribute("loginMember") Member loginMember, String memberNickname,
			String memberIntro, RedirectAttributes ra) {
		loginMember.setMemberNickname(memberNickname);
		loginMember.setMemberIntro(memberIntro);
		
		int result = service.myPageEdit(loginMember);
		
		if(result > 0) {
			ra.addFlashAttribute("message", "수정 성공");
		} else {
			ra.addFlashAttribute("message", "수정 실패");
		}
		return "redirect:main";
	}
	
	@PostMapping("edit/profile")
	@ResponseBody
	public int myPageEditProfile(@RequestBody MultipartFile memberProfile,
			@SessionAttribute("loginMember") Member loginMember) throws IllegalStateException, IOException {
		
		int result = service.myPageEditProfile(memberProfile, loginMember);
		
		return result;
	}
	
	// 닉네임 유효성 검사 비동기
	@ResponseBody
	@GetMapping("validation")
	public int myPageValidation(String memberNickname) {
		return service.myPageValidation(memberNickname);
	}
	
	// 비밀번호 변경 창
	@GetMapping("edit/pwChange")
	public String myPageEditPwPopup() {
		return "my_page/edit/popup";
	}
	
	// 팝업 비밀번호 변경
	@PostMapping("edit/pwChange")
	public String myPageEditPw(@SessionAttribute("loginMember") Member loginMember, MyPagePwChange pwChange
			, RedirectAttributes ra) {
		pwChange.setMemberNo(loginMember.getMemberNo());
		int result = service.myPageEditPw(pwChange);
		
		String path;
		String message;
		if(result > 0) {
			message = "비밀번호 변경 성공";
			path = "redirect:edit";
		} else if(result == -1) {
			message = "현재 비밀번호가 일치하지 않습니다";
			path = "redirect:pwChange";
		} else {
			message = "비밀번호 변경 실패";
			path = "redirect:pwChange";
		}
		ra.addFlashAttribute("message", message);
		
		return path;
	}
	
}
