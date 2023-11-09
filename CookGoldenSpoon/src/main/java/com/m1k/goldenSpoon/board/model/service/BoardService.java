package com.m1k.goldenSpoon.board.model.service;

import java.util.Map;

import com.m1k.goldenSpoon.board.model.dto.Board;

public interface BoardService {

	/** 자유게시판 전체 조회
	 * @param boardCode
	 * @param cp 
	 * @return
	 */
	Map<String, Object> selectAllBoard(int boardCode, int cp);

	/** 자유게시판 상세 조회
	 * @param map
	 * @return
	 */
	Board boardDetail(Map<String, Object> map);

	/** 게시글 조회 수 증가
	 * @param boardNo
	 * @return
	 */
	int updateBoardHits(int boardNo);

}
