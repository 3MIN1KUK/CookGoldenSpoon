package com.m1k.goldenSpoon.cs.model.service;

import java.util.Map;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;

public interface InquiryService {

	/** 문의사항 전체 조회
	 * @param cp
	 * @param order
	 * @return
	 */
	Map<String, Object> selectAllInquiry(int cp, int order);

	/** 문의사항 상세 조회
	 * @param inquiryNo
	 * @return
	 */
	Inquiry inquiryDetail(int inquiryNo);


	
	
}
