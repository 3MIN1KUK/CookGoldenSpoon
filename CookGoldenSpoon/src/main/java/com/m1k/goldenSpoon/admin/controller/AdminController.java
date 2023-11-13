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
import org.springframework.web.context.annotation.RequestScope;

import com.m1k.goldenSpoon.admin.model.dto.unionComment;
import com.m1k.goldenSpoon.admin.model.service.AdminService;
import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.member.model.dto.Instructor;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("admin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService service;

	
	@GetMapping("boardSearch")
	public String boardSearch() {
		return "admin/board_search";
	}
	
	@GetMapping("recipeSearch")
	public String recipeSearch() {
		return "admin/recipe_search";
	}
	
	 
	@GetMapping("commentSearch")
	public String commentSearch() {
		return "admin/comment_search";
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
	public String recipeResult(Recipe searchRecipe, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		int memberNo = searchRecipe.getMemberNo();
		Map<String, Object> map = service.recipeResult(searchRecipe, cp);
		model.addAttribute("memberNo",memberNo);
		model.addAttribute("map", map);
		
		return "admin/recipe_result";
	}
	
	// 회원 게시판 조회
	@GetMapping("boardResult")
	public String boardResult(Board searchBoard, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		Map<String, Object> map = service.boardResult(searchBoard, cp);
		model.addAttribute("map",map);

		return "admin/board_result";
	}
	
	// 댓글 통합 검색
	@GetMapping("commentResult")
	public String commentResult(unionComment searchComment, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		Map<String, Object> map = service.commentResult(searchComment, cp);
		model.addAttribute("map",map);
		
		return "admin/comment_result";
	}
	
	@GetMapping("instructorApproval")
	public String instructorApproval(Instructor searchInstructor, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		Map<String, Object> map = service.instructorApproval(searchInstructor, cp);
		
		model.addAttribute("map", map);
		
		return "admin/instructor_approval";
	}
	
	// 게시글 삭제 
	@PutMapping("boardDelete")
	@ResponseBody
	public int boardDelete(@RequestBody int boardNo) {
		
		return service.boardDelete(boardNo);
	}
	
	@GetMapping(value = "createBoardList", produces = "application/json")
	@ResponseBody
	public Map<String, Object> createBoardList(Board searchBoard,@RequestParam int cp){
		
		Map<String, Object> map = service.boardResult(searchBoard, cp);
		
		return map;
	}

	
	
}





