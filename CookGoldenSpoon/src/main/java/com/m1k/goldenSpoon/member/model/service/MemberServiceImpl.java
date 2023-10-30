package com.m1k.goldenSpoon.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper mapper;
	private final BCryptPasswordEncoder bcrypt;
	
	@Override
	public String login(String memberId, String memberPw) {
		
//		Member loginMember = mapper.login(memberId);
//		
//		if (bcrypt.matches(memberPw, loginMember.getMemberPw() ) ) {
//			return null;
//		}
		
		return null;
	}
	
	@Override
	public int signup(Member signupMember) {
		
//		int result = mapper.signup(signupMember);
		
		return 0;
	}

}
