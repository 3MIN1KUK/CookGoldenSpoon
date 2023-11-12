package com.m1k.goldenSpoon.cs.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.cs.model.dto.Inquiry;
import com.m1k.goldenSpoon.cs.model.dto.Notice;
import com.m1k.goldenSpoon.cs.model.mapper.CsMapper;
import com.m1k.goldenSpoon.cs.model.mapper.InquiryMapper;

import lombok.RequiredArgsConstructor;

@Transactional(rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService{

	private final InquiryMapper mapper;
	
	/** 문의사항 전체 조회
	 *
	 */
	@Override
	public Map<String, Object> selectAllInquiry(int cp, int order) {
		
		// 전체 글 수 조회
		int listCount = mapper.getInquiryListCount();
		
		Pagination pagination = new Pagination(cp, listCount, 14, 7);
		
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> noticeList = mapper.selectAllInquiry(order, rowBounds);
		Map<String, Object> map = new HashMap<>();
		map.put("noticeList", noticeList);
		map.put("pagination", pagination);
		return map;
		
	}
	
	/** 문의사항 상세 조회
	 *
	 */
	@Override
	public Inquiry inquiryDetail(int inquiryNo) {
		return mapper.inquiryDetail(inquiryNo);
	}
	
}
