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
	String findMember(Member searchMember);

	/** 비밀번호 바꾸기
	 * @param memberNo
	 * @param memberPw
	 * @return
	 */
	int changePw(int memberNo, String memberPw);

	/** 이메일 중복 체크
	 * @param email
	 * @return
	 */
	int checkEamil(String email);

	/** 닉네임 중복 체크
	 * @param nickname
	 * @return
	 */
	int checkNickname(String nickname);
	

	/** 아이디 중복 체크
	 * @param id
	 * @return
	 */
	int checkId(String id);

	/** 빠른 로그인
	 * @param memberEmail
	 * @return
	 */
	Member quickLogin(String memberId);


}
