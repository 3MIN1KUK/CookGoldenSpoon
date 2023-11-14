package com.m1k.goldenSpoon.cs.model.service;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;

public interface EditInquiryService {

	/** 문의 작성(삽입)
	 * @param inquiry
	 * @return result
	 */
	int insertInquiry(Inquiry inquiry);

	/** 문의 답글 작성
	 * @param inquiry
	 * @return
	 */
	int replyInquiry(Inquiry updateInquiry);


}
