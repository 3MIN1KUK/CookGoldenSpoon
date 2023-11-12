package com.m1k.goldenSpoon.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.m1k.goldenSpoon.board.model.dto.BoardComment;

@Mapper
public interface BoardCommentMapper {

	/** 댓글 목록 조회
	 * @param boardNo
	 * @return
	 */
	List<BoardComment> select(int boardNo);

	/** 댓글 등록
	 * @param boardComment
	 * @return
	 */
	int insert(BoardComment boardComment);

	/** 댓글 수정
	 * @param boardComment
	 * @return
	 */
	int update(BoardComment boardComment);

	/** 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	int delete(int commentNo);

}
