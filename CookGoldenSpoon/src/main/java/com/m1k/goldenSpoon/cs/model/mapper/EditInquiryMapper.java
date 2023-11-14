package com.m1k.goldenSpoon.cs.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;

@Mapper
public interface EditInquiryMapper {

	/** 문의사항 작성
	 * @param inquiry
	 * @return
	 */
	int insertInquiry(Inquiry inquiry);

	/** 문의 답글 작성
	 * @param inquiry
	 * @return
	 */
	int replyInquiry(Inquiry updateInquiry);

}
