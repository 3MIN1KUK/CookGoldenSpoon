package com.m1k.goldenSpoon.admin.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import com.m1k.goldenSpoon.admin.model.dto.unionComment;
import com.m1k.goldenSpoon.admin.model.service.AdminService;
import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.Report;
import com.m1k.goldenSpoon.member.model.dto.Instructor;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	
	// 댓글 비동기 삭제
	@PutMapping("commentDelete")
	@ResponseBody
	public int commentDelete(@RequestBody unionComment deleteComment) {
		return service.commentDelete(deleteComment);
	}
	
	// 댓글 비동기 검색
	@GetMapping(value = "createCommentList", produces = "application/json")
	@ResponseBody
	public Map<String, Object> createCommentList(unionComment searchComment, Model model, int cp) {
		
		Map<String, Object> map = service.commentResult(searchComment, cp);
		
		return map;
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
	
	// 게시글 비동기 검색
	@GetMapping(value = "createBoardList", produces = "application/json")
	@ResponseBody
	public Map<String, Object> createBoardList(Board searchBoard, @RequestParam int cp){
		
		Map<String, Object> map = service.boardResult(searchBoard, cp);
		
		return map;
	}

	// 신고 이동 및 검색
	@GetMapping("reportManagement")
	public String reportManagement(Report report, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		if (report.getSelectOption() == null) {
			Map<String, Object> map = service.selectReport(cp);
			model.addAttribute("map", map);
		} else {
			Map<String, Object> map = service.searchReport(report, cp);
			model.addAttribute("map", map);
		}
		
		
		return "admin/report_management";
	}
	
	// 신고 상세 조회
	@GetMapping(value = "reportDetail", produces = "application/json")
	@ResponseBody
	public Report reportDetail(int reportNo,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		return service.reportDetail(reportNo, cp);
	}
	
	// 신고 답변 입력
	@PostMapping("reportAnswer")
	@ResponseBody
	public int reportAnswer(@RequestBody Report report) {
		return service.reportAnswer(report);
	}
	
	@PutMapping("changeMemberDelFl")
	@ResponseBody
	public int changeMemberDelFl(@RequestBody Member member) {
		return service.changeMemberDelFl(member);
	}
	
	@PutMapping("changeBoardDelFl")
	@ResponseBody
	public int changeBoardDelFl(@RequestBody Board board) {
		return service.changeBoardDelFl(board);
	}
	
	
	@PutMapping("changeCommentDelFl")
	@ResponseBody
	public int changeCommentDelFl(@RequestBody unionComment comment) {
		return service.changeCommentDelFl(comment);
	}
	
}





