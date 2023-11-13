package com.m1k.goldenSpoon.cs.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;
import com.m1k.goldenSpoon.cs.model.dto.Notice;

@Mapper
public interface InquiryMapper {

	/** 문의사항 글 수 조회
	 * @return
	 */
	int getInquiryListCount();

	/** 문의사항 전체 조회
	 * @param order
	 * @param rowBounds
	 * @return
	 */
	List<Notice> selectAllInquiry(int order, RowBounds rowBounds);

	/** 문의사항 상세 조회
	 * @param inquiryNo
	 * @return
	 */
	Inquiry inquiryDetail(int inquiryNo);


}
