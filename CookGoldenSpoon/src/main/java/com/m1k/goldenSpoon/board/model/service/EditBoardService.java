package com.m1k.goldenSpoon.board.model.service;

import java.util.Map;

public interface EditBoardService {

	/** 게시글 삭제
	 * @param paramMap
	 * @return
	 */
	int deleteBoard(Map<String, Integer> paramMap);

}
