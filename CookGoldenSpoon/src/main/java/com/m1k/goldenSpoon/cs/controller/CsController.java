package com.m1k.goldenSpoon.cs.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.cs.model.dto.Notice;
import com.m1k.goldenSpoon.cs.model.service.CsService;
import com.m1k.goldenSpoon.member.model.dto.Member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("cs")
@RequiredArgsConstructor
public class CsController {
	
	private final CsService service;
	
	// 공지사항 ------------------------------------------------------------------------------------
	@GetMapping("notice")
	public String notice(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam(value = "order", required = false, defaultValue = "1") int order) {
		Map<String, Object> map = service.selectAllNotice(cp, order);
		model.addAttribute("map", map);
		return "cs/notice/notice";
	}
	
	@GetMapping(value = "notice/select", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> noticeSelect(String searchNotice, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		return service.noticeSelect(cp, searchNotice);
	}
	
	@GetMapping("notice/{noticeNo:[0-9]+}")
	public String noticeDetail(@PathVariable("noticeNo") int noticeNo, Model model, Board board,
			RedirectAttributes ra, HttpServletRequest req, HttpServletResponse resp,
			@SessionAttribute(value="loginMember", required=false) Member loginMember) {
		Notice notice = service.noticeDetail(noticeNo);
		model.addAttribute("notice", notice);
		return "cs/notice/noticeDetail";
		
		
			
			
		}

	
	// QnA ---------------------------------------------------------------------------------------
	@GetMapping("qna")
	public String qna() {
		return "cs/qna/qna";
	}
	
	
	// 신고 ------------------------------------------------------------------------------------
	@GetMapping("report")
	public String report() {
		return "cs/report/report";
	}

	
}
