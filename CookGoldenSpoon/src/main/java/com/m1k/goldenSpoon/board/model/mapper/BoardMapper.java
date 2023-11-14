package com.m1k.goldenSpoon.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.board.model.dto.Board;

import jakarta.mail.internet.ParseException;

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
	List<Board> selectAllBoard(int boardCode, RowBounds rowBounds) throws ParseException;

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

	/** 게시판 종류 조회
	 * @param boardCode
	 * @return
	 */
	String selectBoardType(int boardCode) ;

	/** 검색어 일치 게시글 수 조회
	 * @param paramMap
	 * @return
	 */
	int searchListCount(Map<String, Object> paramMap);

	/** 검색어 일치 게시글 목록 조회
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Board> searchAllBoard(Map<String, Object> paramMap, RowBounds rowBounds);

}
