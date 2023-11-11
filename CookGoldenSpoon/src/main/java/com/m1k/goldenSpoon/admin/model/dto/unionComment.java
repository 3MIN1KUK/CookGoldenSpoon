package com.m1k.goldenSpoon.admin.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class unionComment {
	private int commentParentNo;
	private String commentEnrollDate;
	private String commentContent;
	private String commentParentTitle;
	private int commentNo;
	private int memberNo;
	private String memberNickname;
	private String commentDelFl;
	private String commentType;
}
