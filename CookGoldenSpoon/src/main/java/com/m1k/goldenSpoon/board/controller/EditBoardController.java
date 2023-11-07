package com.m1k.goldenSpoon.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.m1k.goldenSpoon.board.model.service.EditBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("editBoard")
public class EditBoardController {
	
	private final EditBoardService service;
	
	@GetMapping("insert")
	public String insertBoard() {
		return "board/boardWrite";
	}
	
	
}
