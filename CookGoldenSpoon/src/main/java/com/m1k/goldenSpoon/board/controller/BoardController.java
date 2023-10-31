package com.m1k.goldenSpoon.board.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.service.BoardService;
import com.m1k.goldenSpoon.cs.model.dto.Notice;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	// 자유 게시판 전체 조회
	@GetMapping("select")
	public String notice(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp
			) {
		Map<String, Object> map = service.selectAllBoard(cp);
		model.addAttribute("map", map);
		return "board/board";
	}
	
	
	@GetMapping("select/{boardNo:[0-9]+}")
	public String boardDetail(@PathVariable("boardNo") int boardNo, Model model) {
		Board board = service.boardDetail(boardNo);
		model.addAttribute("board", board);
		return "board/boardDetail";
	}
	
}
