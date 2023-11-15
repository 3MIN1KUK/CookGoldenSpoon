package com.m1k.goldenSpoon.board.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Report {
	private int memberNo;
	private String memberNickname;
	
	private int boardCode;
	private int boardNo;
	private String boardTitle;
	
	private int reportNo;
	private int reporterNo;
	private String reportContent;
	private int reportCommentNo;
	private String reporterNickname;
	private String reportTitle;
	
	private String selectOption;
	private String reportAnswer;
	private int commentBoardNo;
	private String type;
}
