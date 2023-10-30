package com.m1k.goldenSpoon.member.model.service;

import com.m1k.goldenSpoon.member.model.dto.Member;

public interface MemberService {

	/** 로그인
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	Member login(String memberEmail, String memberPw);

	/** 회원가입
	 * @param signupMember
	 * @return
	 */
	int signup(Member signupMember);

}
