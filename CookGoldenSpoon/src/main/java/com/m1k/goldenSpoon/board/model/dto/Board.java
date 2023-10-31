package com.m1k.goldenSpoon.board.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardEnrollDate;
	private String boardDelFl;
	private int boardHits;
	private int boardCode;
	
	
	
}
