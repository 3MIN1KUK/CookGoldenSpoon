package com.m1k.goldenSpoon.cs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String notice(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam(value = "order", required = false, defaultValue = "1") int order) {
		Map<String, Object> map = service.selectAllNotice(cp, order);
		model.addAttribute("map", map);
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
	
	@GetMapping("notice/{noticeNo:[0-9]+}")
	public String noticeDetail(@PathVariable("noticeNo") int noticeNo, Model model) {
		Notice notice = service.noticeDetail(noticeNo);
		model.addAttribute("notice", notice);
		return "cs/notice/noticeDetail";
	}
}
