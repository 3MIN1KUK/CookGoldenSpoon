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
import com.m1k.goldenSpoon.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Transactional(rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService{

	private final InquiryMapper mapper;
	
	@Override
	public Map<String, Object> selectAllInquiry(int cp, int memberNo) {
		
		int listCount = mapper.getInquiryListCount(memberNo);
		
		Pagination pagination = new Pagination(cp, listCount, 14, 7);
		
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Inquiry> inquiryList = mapper.selectAllInquiry(memberNo, rowBounds);
		Map<String, Object> map = new HashMap<>();
		map.put("inquiryList", inquiryList);
		map.put("pagination", pagination);

		return map;
	}
	
	@Override
	public Inquiry inquiryDetail(int inquiryNo) {
		return mapper.inquiryDetail(inquiryNo);
	}
}
