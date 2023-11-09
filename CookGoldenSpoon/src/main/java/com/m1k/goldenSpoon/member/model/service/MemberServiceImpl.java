package com.m1k.goldenSpoon.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper mapper;
	private final BCryptPasswordEncoder bcrypt;
	
	// 로그인
	@Override
	public Member login(String memberId, String memberPw) {
		
		Member loginMember = mapper.login(memberId);
		
		if (loginMember == null) {
			return null;
		}
		
		if (!bcrypt.matches(memberPw, loginMember.getMemberPw() ) ) {
			return null;
		}
		
		return loginMember;
	}
	
	// 회원가입
	@Override
	public int signup(Member signupMember) {
		
		signupMember.setMemberPw(bcrypt.encode(signupMember.getMemberPw() ) );
		int result = mapper.signup(signupMember);
		
		return result;
	}
	
	
	// 아이디 찾기
	@Override
	public String findId(String memberEmail) {
		
		String memberId = mapper.findId(memberEmail);
		
		return memberId;
	}
	
	// 비밀번호 변경할 아이디 조회
	@Override
	public String findMember(Member searchMember) {
		
		String memberNo = mapper.findMember(searchMember);
		
		return memberNo;
	}
	
	// 비밀번호 변경
	@Override
	public int changePw(int memberNo, String memberPw) {
		
		memberPw = bcrypt.encode(memberPw);
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberPw", memberPw);
		map.put("memberNo", memberNo);
		
		int result = mapper.changePw(map);
		
		return result;
	}
	
	// 이메일 중복 체크
	@Override
	public int checkEamil(String email) {
		return mapper.checkEmail(email);
	}
	
	// 닉네임 중복체크
	@Override
	public int checkNickname(String nickname) {
		return mapper.checkNickname(nickname);
	}

	
	// 빠른 로그인
	@Override
	public Member quickLogin(String memberId) {
		return mapper.login(memberId);
	}
	
}
