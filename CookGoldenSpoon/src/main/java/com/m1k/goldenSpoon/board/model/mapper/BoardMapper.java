package com.m1k.goldenSpoon.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	/** 자유게시판 전체글 수 조회
	 * @return
	 */
	int getBoardListCount();

	/** 자유게시판 전체 조회
	 * @param rowBounds
	 * @return
	 */
	List<Board> selectAllBoard(RowBounds rowBounds);

	/** 자유게시판 상세조회
	 * @param boardNo
	 * @return
	 */
	Board boardDetail(int boardNo);

}
