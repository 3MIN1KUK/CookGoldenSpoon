package com.m1k.goldenSpoon.board.model.service;

import java.util.Map;

import com.m1k.goldenSpoon.board.model.dto.Board;

public interface BoardService {

	/** 자유게시판 전체 조회
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectAllBoard(int cp);

	/** 자유게시판 상세 조회
	 * @param boardNo
	 * @return
	 */
	Board boardDetail(int boardNo);

}
