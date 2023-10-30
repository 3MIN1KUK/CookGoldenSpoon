package com.m1k.goldenSpoon.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	/** 로그인
	 * @param memberId
	 * @return
	 */
	Member login(String memberEmail);

	/** 회원가입
	 * @param signupMember
	 * @return
	 */
	int signup(Member signupMember);

}
