package com.m1k.goldenSpoon.member.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Instructor {

	private String memberId;
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberNickname;
	private String memberEnrollDate;
	private String memberDelFl;
	private int memberAuthority;
	
	private String memberName;
	private String memberTel;
	private String memberAddress;
	private String memberPlan;
	private String memberApproval;
	private int memberRating;
}
