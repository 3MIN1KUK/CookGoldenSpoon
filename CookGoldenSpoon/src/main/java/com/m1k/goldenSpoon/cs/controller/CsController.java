package com.m1k.goldenSpoon.cs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.m1k.goldenSpoon.cs.model.dto.Notice;
import com.m1k.goldenSpoon.cs.model.service.CsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("cs")
@RequiredArgsConstructor
public class CsController {
	
	private final CsService service;
	
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

	@GetMapping(value = "notice/select", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Notice> noticeSelect(String searchNotice) {
		return service.noticeSelect(searchNotice);
	}
	
}
