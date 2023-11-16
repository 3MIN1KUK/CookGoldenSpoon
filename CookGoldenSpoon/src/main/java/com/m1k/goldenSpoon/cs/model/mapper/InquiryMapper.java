package com.m1k.goldenSpoon.cs.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.cs.model.dto.Inquiry;
import com.m1k.goldenSpoon.cs.model.dto.Notice;
import com.m1k.goldenSpoon.member.model.dto.Member;

@Mapper
public interface InquiryMapper {

	/** 문의사항 개수 조회
	 * @param memberNo
	 * @return
	 */
	int getInquiryListCount(int memberNo);

	/** 문의사항 전체 조회
	 * @param order
	 * @param rowBounds
	 * @return
	 */
	List<Inquiry> selectAllMyInquiry(int memberNo, RowBounds rowBounds);

	/** 문의사항 상세 조회
	 * @param inquiryNo
	 * @return
	 */
	Inquiry inquiryDetail(int inquiryNo);

	/** 관리자용 문의사항 전체조회 개수
	 * @return
	 */
	int getAllInquiryListCount();

	/** 관리자용 문의사항 전체조회
	 * @param rowBounds
	 * @return
	 */
	List<Inquiry> selectAllInquiry(RowBounds rowBounds);
	

}
