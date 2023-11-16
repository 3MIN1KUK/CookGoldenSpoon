package com.m1k.goldenSpoon.cs.model.service;

import java.util.Map;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;
import com.m1k.goldenSpoon.member.model.dto.Member;

public interface InquiryService {

	/** 문의사항 전체 조회
	 * @param cp
	 * @param memberAuthority 
	 * @param order
	 * @return
	 */
	Map<String, Object> selectAllInquiry(int cp, int memberNo, int memberAuthority);

	/** 문의사항 상세 조회 화면 전환
	 * @param inquiryNo
	 * @return
	 */
	Inquiry inquiryDetail(int inquiryNo);



	
	
}
