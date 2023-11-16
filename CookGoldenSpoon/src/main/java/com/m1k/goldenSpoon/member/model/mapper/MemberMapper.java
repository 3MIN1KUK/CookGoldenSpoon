package com.m1k.goldenSpoon.member.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	/** 로그인
	 * @param memberId
	 * @return
	 */
	Member login(String memberId);

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

	/** 아이디 검색
	 * @param searchMember
	 * @return
	 */
	String findMember(Member searchMember);

	/** 비밀번호 바꾸기
	 * @param map
	 * @return
	 */
	int changePw(Map<String, Object> map);

	/** 이메일 중복 체크
	 * @param email
	 * @return
	 */
	int checkEmail(String email);

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

}
