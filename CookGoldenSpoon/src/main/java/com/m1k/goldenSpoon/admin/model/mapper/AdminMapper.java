package com.m1k.goldenSpoon.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.member.model.dto.Member;

@Mapper
public interface AdminMapper {

	/** 회원 수 조회
	 * @return
	 */
	int selectListCount();

	/** 회원 조회
	 * @param searchMember
	 * @param rowBounds
	 * @return
	 */
	List<Member> selectMember(RowBounds rowBounds);

	/** 검색 회원 수 조회
	 * @param paramMap
	 * @return
	 */
	int searchListCount(Map<String, Object> paramMap);

	/** 회원 검색 조회
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Member> searchMember(Map<String, Object> paramMap, RowBounds rowBounds);

	/** 회원 권한 변경
	 * @param member
	 * @return
	 */
	int changeAuthority(Member member);

}
