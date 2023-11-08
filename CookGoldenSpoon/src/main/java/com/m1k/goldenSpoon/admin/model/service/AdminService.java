package com.m1k.goldenSpoon.admin.model.service;

import java.util.List;
import java.util.Map;

import com.m1k.goldenSpoon.member.model.dto.Member;

public interface AdminService {

	/** 회원 조회
	 * @param searchMember
	 * @param cp 
	 * @return
	 */
	Map<String, Object> selectMember(int cp);

	/** 회원 검색 조회
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> searchMember(Map<String, Object> paramMap, int cp);

}
