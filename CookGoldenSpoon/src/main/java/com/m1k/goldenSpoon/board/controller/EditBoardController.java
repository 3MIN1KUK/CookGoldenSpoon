package com.m1k.goldenSpoon.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.service.EditBoardService;
import com.m1k.goldenSpoon.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("editBoard")
@SessionAttributes({"loginMember"})
public class EditBoardController {
	
	private final EditBoardService service;
	
	/** 게시글 작성 전환
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/insert")
	public String insertBoard(
			@PathVariable("boardCode") int boardCode,
			@SessionAttribute(value="loginMember", required = false) Member loginMember) {
		if(loginMember == null) {
			return "redirect:/board/"+ boardCode;
		}
		return "board/boardWrite";
	}
	
	
	/** 게시글 삭제
	 * @param boardCode
	 * @param boardNo
	 * @param loginMember
	 * @param ra
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}/delete")
	public String deleteBoard(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			@SessionAttribute("loginMember") Member loginMember,
			RedirectAttributes ra
			) {
		
		// 로그인 상태가 아닌 경우 로그인 창으로 보내기
		if(loginMember == null) { // 로그인 X
			ra.addFlashAttribute("message", "로그인 후 이용해주세요");
			return "redirect:/member/login";
		}
		
		// Map을 이용해서 boardCode, boardNo, memberNo까지 담기
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("boardCode", boardCode);
		paramMap.put("boardNo", boardNo);
		paramMap.put("memberNo", loginMember.getMemberNo());
		
		// 서비스 호출 후 결과 반환
		int result = service.deleteBoard(paramMap);
		
		String path = null;
		String message = null;
		
		if(result > 0) { // 결과 반환 했을 때
			message = "삭제 되었습니다.";
			path = "redirect:/board/"+boardCode; // 해당 게시판 첫 페이지
		} else {
			message = "삭제 실패";
			path = "redirect:/";
		}
		ra.addFlashAttribute("message", message);
		return path;
	}
	
	/** 게시글 작성
	 * @param boardCode : 게시판 코드
	 * @param loginMember : 로그인한 회원의 정보가 담긴 객체
	 * @param board : 파라미터가 담긴 커맨드 객체
	 * @param images : 사진 
	 * @param ra
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("{boardCode:[0-9]+}/insert")
	public String insertBoard( 
			@PathVariable("boardCode") int boardCode,
			@SessionAttribute("loginMember") Member loginMember,
			Board board, 
			@RequestParam("images") List<MultipartFile> images,
			RedirectAttributes ra
			) throws IllegalStateException, IOException {
		
		// 로그인한 회원의 번호, 게시판 코드 board에 세팅
		board.setMemberNo(loginMember.getMemberNo());
		board.setBoardCode(boardCode);
		
		int boardNo = service.insertBoard(board, images);
		
		if(boardNo > 0) {
			ra.addFlashAttribute("message", "게시글 작성 성공");
			return String.format("redirect:/board/%d/%d", boardCode, boardNo);
		}
		
		ra.addFlashAttribute("message", "게시글이 올바르게 작성되지 않았습니다.");
		return "redirect:insert";
		
	}
	
	
}
