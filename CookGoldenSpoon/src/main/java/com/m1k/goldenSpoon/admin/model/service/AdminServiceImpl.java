package com.m1k.goldenSpoon.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.admin.model.mapper.AdminMapper;
import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final AdminMapper mapper;
	
	// 회원 검색
	@Override
	public Map<String, Object> selectMember( int cp) {
		
		
		int listCount = mapper.selectListCount();
		
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> listMember = mapper.selectMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("listMember", listMember);
		map.put("pagination", pagination);
		
		return map;
	}
	
	// 회원 검색
	@Override
	public Map<String, Object> searchMember(Map<String, Object> paramMap, int cp) {
		
		
		int listCount = mapper.searchListCount(paramMap);
		
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> listMember = mapper.searchMember(paramMap, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("listMember", listMember);
		map.put("pagination", pagination);
		
		return map;
	}
	
	// 권한 변경
	@Override
	public int changeAuthority(Member member) {
		return mapper.changeAuthority(member);
	}
	
	
	@Override
	public Member memberDetail(int memberNo) {
		return mapper.memberDetail(memberNo);
	}
	
	
}
