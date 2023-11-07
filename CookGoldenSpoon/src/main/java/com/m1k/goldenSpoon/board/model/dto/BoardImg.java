package com.m1k.goldenSpoon.board.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardImg {

	private int boardImageNo;
	private String boardImage;
	private int boardImageOrder;
	private int boardNo;
	private String boardImageRename;
	private String boardImageName;
	
	private MultipartFile uploadFile;
	
}
