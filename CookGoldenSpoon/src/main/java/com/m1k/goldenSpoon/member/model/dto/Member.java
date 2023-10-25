package com.m1k.goldenSpoon.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class Member {
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberBirth;
	private String memberEmail;
	private String memberTel;
	private String memberAddress;
	private String memberAddress2;
}
