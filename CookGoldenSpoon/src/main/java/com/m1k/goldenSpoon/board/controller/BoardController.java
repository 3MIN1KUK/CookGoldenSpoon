package com.m1k.goldenSpoon.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("board")
@SessionAttributes({"loginMember"})
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	/** 자유게시판 전체 조회
	 * @param model
	 * @param cp
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}")
	public String notice(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@PathVariable("boardCode") int boardCode) {
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
		Model model
		) {
		Map<String, Object> map = new HashMap<>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		Board board = service.boardDetail(map);
		model.addAttribute("board", board);
		return "board/boardDetail";
	}
	
}