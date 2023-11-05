package com.m1k.goldenSpoon.member.model.service;

import com.m1k.goldenSpoon.member.model.dto.Member;

public interface MemberService {

	/** 로그인
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	Member login(String memberId, String memberPw);

	/** 회원가입
	 * @param signupMember
	 * @return
	 */
	int signup(Member signupMember);

	/** 아이디 찾기
	 * @param memberEmail
	 * @return
	 */
	String findId(String memberEmail);

	/** 아이디 , 이메일로 검색
	 * @param searchMember
	 * @return
	 */
	int findMember(Member searchMember);

	/** 비밀번호 바꾸기
	 * @param memberNo
	 * @param memberPw
	 * @return
	 */
	int changePw(int memberNo, String memberPw);

}
