package com.m1k.goldenSpoon.cs.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Notice {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String memberNickname;
	private String boardEnrollDate;
	private int boardHits;
	private int boardCode;
	

	private String memberNo;
}
