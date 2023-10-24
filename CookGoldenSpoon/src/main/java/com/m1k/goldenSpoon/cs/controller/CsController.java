package com.m1k.goldenSpoon.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.m1k.goldenSpoon.cs.model.service.CsService;

@Controller
@RequestMapping("cs")
public class CsController {
	
	@Autowired
	private CsService service;
	
	@GetMapping("notice")
	public String notice() {
		return "cs/notice/notice";
	}
	@GetMapping("qna")
	public String qna() {
		return "cs/qna/qna";
	}
	@GetMapping("inquiry")
	public String inquiry() {
		return "cs/inquiry/inquiry";
	}
	@GetMapping("report")
	public String report() {
		return "cs/report/report";
	}

}
