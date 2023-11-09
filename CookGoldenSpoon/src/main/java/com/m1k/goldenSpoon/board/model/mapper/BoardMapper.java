package com.m1k.goldenSpoon.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	/** 자유게시판 전체글 수 조회
	 * @param boardCode 
	 * @return
	 */
	int getBoardListCount(int boardCode);

	/** 자유게시판 전체 조회
	 * @param boardCode 
	 * @param rowBounds
	 * @return
	 */
	List<Board> selectAllBoard(int boardCode, RowBounds rowBounds);

	/** 자유게시판 상세조회
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
