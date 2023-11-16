package com.m1k.goldenSpoon.cs.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;
import com.m1k.goldenSpoon.cs.model.service.InquiryService;
import com.m1k.goldenSpoon.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("cs")
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class InquiryController {
	
	private final InquiryService service;
	
	/** 문의사항 전체 조회
	 * @param model
	 * @param cp
	 * @param order
	 * @return
	 */
	@GetMapping("inquiry")
	public String inquiry( Model model, 
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra
			) {
		
		if(loginMember != null) {
			
			Map<String, Object> map = service.selectAllInquiry(cp, loginMember.getMemberNo(), loginMember.getMemberAuthority());
			model.addAttribute("map", map);
			return "cs/inquiry/inquiry";
		}
		ra.addFlashAttribute("message", "로그인 후 이용하시기 바랍니다.");
		return "redirect:/member/login";
	}
	
	/** 문의사항 상세 조회
	 * @param inquiryNo
	 * @param model
	 * @return
	 */
	@GetMapping("inquiry/{inquiryNo:[0-9]+}")
	public String inquiryDetail(@PathVariable("inquiryNo") int inquiryNo, Model model) {
		Inquiry inquiry = service.inquiryDetail(inquiryNo);
		model.addAttribute("inquiry", inquiry);
		return "cs/inquiry/inquiryDetail";
	}
	
	

}
