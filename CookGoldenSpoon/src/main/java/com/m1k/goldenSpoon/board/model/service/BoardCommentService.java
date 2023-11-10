package com.m1k.goldenSpoon.board.model.service;

import java.util.List;

import com.m1k.goldenSpoon.board.model.dto.BoardComment;

public interface BoardCommentService {

	/** 댓글 목록 확인
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
