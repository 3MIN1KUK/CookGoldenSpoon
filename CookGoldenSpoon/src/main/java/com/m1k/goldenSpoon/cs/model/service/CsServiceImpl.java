package com.m1k.goldenSpoon.cs.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.cs.model.dto.Notice;
import com.m1k.goldenSpoon.cs.model.mapper.CsMapper;

import lombok.RequiredArgsConstructor;

@Transactional(rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class CsServiceImpl implements CsService{

	private final CsMapper mapper;
	
	@Override
	public Map<String, Object> noticeSelect(int cp, String searchNotice) {
		int listCount = mapper.getNoticeListCount();
		Pagination pagination = new Pagination(cp, listCount, 14, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> noticeList = mapper.noticeSelect(searchNotice, rowBounds);
		Map<String, Object> map = new HashMap<>();
		map.put("noticeList", noticeList);
		map.put("pagination", pagination);
		return map;
	}
	@Override
	public Map<String, Object> selectAllNotice(int cp, int order) {
		
		// 전체 글 수 조회
		int listCount = mapper.getNoticeListCount();
		
		Pagination pagination = new Pagination(cp, listCount, 14, 7);
		
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> noticeList = mapper.selectAllNotice(order, rowBounds);
		Map<String, Object> map = new HashMap<>();
		map.put("noticeList", noticeList);
		map.put("pagination", pagination);
		return map;
	}
	
	@Override
	public Notice noticeDetail(int noticeNo) {
		return mapper.noticeDetail(noticeNo);
	}
}
