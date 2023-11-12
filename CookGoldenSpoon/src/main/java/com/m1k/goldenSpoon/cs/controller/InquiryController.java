package com.m1k.goldenSpoon.cs.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;
import com.m1k.goldenSpoon.cs.model.service.InquiryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("inquiry")
@RequiredArgsConstructor
public class InquiryController {
	
	private final InquiryService service;
	
	/** 문의사항 전체 조회
	 * @param model
	 * @param cp
	 * @param order
	 * @return
	 */
	@GetMapping("inquiry")
	public String inquiry(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam(value = "order", required = false, defaultValue = "1") int order) {
		Map<String, Object> map = service.selectAllInquiry(cp, order);
		model.addAttribute("map", map);
		return "cs/inquiry/inquiry";
	}
	
	/** 문의 사항 상세 조회
	 * @param noticeNo
	 * @param model
	 * @return
	 */
	@GetMapping("inquiry/{inquiryNo:[0-9]+}")
	public String inquiryDetail(@PathVariable("inquiryNo") int inquiryNo, Model model) {
		Inquiry inqiry = service.inquiryDetail(inquiryNo);
		model.addAttribute("inquiry", inquiry(null, 0, 0));
		return "cs/inquiry/inquiryDetail";
	}
	
//	@GetMapping("inquiry/{inquiryNo:[0-9]+}")
//	public String inquiryDetail(@PathVariable("inquiryNo") int inquiryNo, Model model) {
//		Inquiry inqiry = service.inquiryDetail(inquiryNo);
//		model.addAttribute("inquiry", inquiry(null, 0));
//		
//	}
	
}
