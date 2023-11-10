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

	/** 권한 변경
	 * @param member
	 * @return
	 */
	int changeAuthority(Member member);

	/** 회원 상세 조회
	 * @param memberNo
	 * @return
	 */
	Member memberDetail(int memberNo);

	Map<String, Object> recipeResult(int memberNo, int cp);

}
