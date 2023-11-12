package com.m1k.goldenSpoon.board.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardComment {
	
	private int boardCommentNo;
	private String boardCommentEnrollDate;
	private String boardCommentContent;
	private String boardCommentDelFl;
	private int memberNo;
	private int boardNo;
	private int boardParentNo;
	
	private String memberNickname;
	private String memberProfile;
	
}
