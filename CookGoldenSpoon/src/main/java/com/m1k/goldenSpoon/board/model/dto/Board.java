package com.m1k.goldenSpoon.board.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Board {
	
	// BOARD 테이블과 매핑되는 필드
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardEnrollDate;
	private String boardDelFl;
	private int boardHits;
	private int boardCode;
	private int memberNo;
	
	// 게시글 이미지 목록
	private List<BoardImg> imageList;
	
	
	
	
}
