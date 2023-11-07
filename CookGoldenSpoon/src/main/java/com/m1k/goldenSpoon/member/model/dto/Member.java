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
	private String memberId;
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberNickname;
	private String memberProfile;
	private String memberEnrollDate;
	private String memberDelFl;
	private int memberAuthority;
	private String memberIntro;
	
	private int likeCount;
	private int bookmarkCount;
	private int recipeCount;
	private int boardCount;
	private int commentCount;
}
