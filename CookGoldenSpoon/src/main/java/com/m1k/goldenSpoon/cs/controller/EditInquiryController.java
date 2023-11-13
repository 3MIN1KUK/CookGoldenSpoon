package com.m1k.goldenSpoon.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.m1k.goldenSpoon.cs.model.service.EditInquiryService;
import com.m1k.goldenSpoon.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("editInquiry")
@SessionAttributes({"loginMember"})
public class EditInquiryController {
	
	private final EditInquiryService service;
	
	/** 문의사항 작성 전환
	 * @param inquiryNo
	 * @param loginMember
	 * @return
	 */
	@GetMapping("insert")
	public String insertInquiry(
			@SessionAttribute(value = "loginMember", required = false) Member loginMember
			) {
		if(loginMember == null) {
			return "redirect:/cs";
		}
		return "cs/inquiry/inquiryWrite";
	}
	
	
	
	
	
}
