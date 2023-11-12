package com.m1k.goldenSpoon.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.BoardImg;
import com.m1k.goldenSpoon.board.model.mapper.BoardMapper;
import com.m1k.goldenSpoon.board.model.service.BoardService;
import com.m1k.goldenSpoon.member.model.dto.Member;

import jakarta.mail.internet.ParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("board")
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class BoardController {
	
	private final BoardService service;
	
	/** 자유게시판 전체 조회
	 * @param model
	 * @param cp
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}")
	public String selectAllBoard(Model model, 
			@RequestParam(value = "cp", required = false, 
			defaultValue = "1") int cp,
			@PathVariable("boardCode") int boardCode
			) {
		Map<String, Object> map = service.selectAllBoard(boardCode, cp);
		model.addAttribute("boardCode", boardCode);
		model.addAttribute("map", map);
		return "board/board";
	}
	
	/** 자유 게시판 상세 조회
	 * @param boardNo
	 * @param model
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public String boardDetail(
		@PathVariable("boardNo") int boardNo, @PathVariable("boardCode") int boardCode,
		Model model, RedirectAttributes ra,
		@SessionAttribute(value="loginMember", required=false) Member loginMember
		) throws ParseException {
		
		// 1. 상세조회 서비스 호출
		Map<String, Object> map = new HashMap<>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		Board board = service.boardDetail(map);
		
		String path = null;
		
		if(board != null) {
			model.addAttribute("board", board);
			path = "board/boardDetail";
		} else {
			path = "redirect:/board/" + boardCode;
			ra.addFlashAttribute("message", "해당 게시글이 존재하지 않습니다.");
		}
		return path;
	}
}