package com.m1k.goldenSpoon.cs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;
import com.m1k.goldenSpoon.cs.model.service.EditInquiryService;
import com.m1k.goldenSpoon.cs.model.service.InquiryService;
import com.m1k.goldenSpoon.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("editInquiry")
@SessionAttributes({"loginMember"})
public class EditInquiryController {
	
	private final EditInquiryService service;
	
	private final InquiryService inquiryService;
	
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
	
	/** 문의사항 작성 (삽입)
	 * @param loginMember
	 * @param inquiry
	 * @param ra
	 * @return
	 */
	@PostMapping("insert")
	public String insertInquiry(
			@SessionAttribute("loginMember") Member loginMember,
			Inquiry inquiry,
			RedirectAttributes ra
			) {
		inquiry.setMemberNo(loginMember.getMemberNo());
		
		int inquiryNo = service.insertInquiry(inquiry);
		
		if(inquiryNo > 0) {
			ra.addFlashAttribute("message", "문의가 정상적으로 처리되었습니다");
			return String.format("redirect:/cs/inquiry/%d", inquiryNo );
		}
		ra.addFlashAttribute("message", "올바르지 않은 문의입니다");
		return "redirect:insert";
	}
	
	/** 문의사항 답글 화면 전환
	 * @param inquiryNo
	 * @param model
	 * @return
	 */
	@GetMapping("{inquiryNo:[0-9]+}/update")
	public String updateReply(
			@PathVariable("inquiryNo") int inquiryNo,
			Model model
			) {
		Inquiry inquiry = inquiryService.inquiryDetail(inquiryNo);
		model.addAttribute("inquiry", inquiry);
		
		return "cs/inquiry/inquiryReply";
	}
	
	/** 문의사항 답글 작성
	 * @param inquiryNo
	 * @param updateInquiry
	 * @param ra
	 * @return
	 */
	@PostMapping("{inquiryNo:[0-9]+}/update")
	public String replyInquiry(
			@PathVariable("inquiryNo") int inquiryNo,
			Inquiry updateInquiry,
			RedirectAttributes ra
			) {
		
		updateInquiry.setInquiryNo(inquiryNo);
		
		int result = service.replyInquiry(updateInquiry);
		
		String message = null;
		String path = null;
		
		if(result > 0) {
			message = "답글 작성이 완료되었습니다";
			path = String.format("redirect:/cs/inquiry/%d", inquiryNo);
		} else {
			message = "작성 실패...";
			path = "redirect:update";
		}
		ra.addFlashAttribute("message", message);
		return path;
	}
	
	
	
}
