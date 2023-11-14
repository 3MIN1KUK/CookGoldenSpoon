package com.m1k.goldenSpoon.cs.model.service;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;
import com.m1k.goldenSpoon.cs.model.mapper.EditInquiryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
public class EditInquiryServiceImpl implements EditInquiryService{
	
	private final EditInquiryMapper mapper;
	
	// 문의 작성
	@Override
	public int insertInquiry(Inquiry inquiry) {
		
		int result = mapper.insertInquiry(inquiry);
		
		if(result == 0) return 0; // 삽입 실패
		
		int inquiryNo = inquiry.getInquiryNo();
		
		return inquiryNo;
	}
	
	// 답글 작성
	@Override
	public int replyInquiry(Inquiry updateInquiry) {
		
		int result = mapper.replyInquiry(updateInquiry);
		
		return result;
	}
}
