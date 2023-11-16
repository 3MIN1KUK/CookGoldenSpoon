package com.m1k.goldenSpoon.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.Report;
import com.m1k.goldenSpoon.board.model.service.ReportBtnService;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.myPage.model.dto.MyPagePwChange;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class ReportBtnController {

	private final ReportBtnService service;
	
		// 신고하기 눌렀을 때 팝업 창
		@GetMapping("csCustomer")
		public String csPopup(Report report, Model model) {
			
			model.addAttribute("report", report);
			
			return "board/boardCs";
		}
		
		// 신고 삽입
		@PostMapping("report/csCustomer")
		@ResponseBody
		public int csPopupBtn(@RequestBody Report report,
				@SessionAttribute("loginMember") Member loginMember,
				 RedirectAttributes ra) {
			report.setMemberNo(loginMember.getMemberNo() );
			report.setMemberNickname(loginMember.getMemberNickname() );
			
			int result = service.csPopupBtn(report);
      
			
			return result;
		}
	
	
	
}
