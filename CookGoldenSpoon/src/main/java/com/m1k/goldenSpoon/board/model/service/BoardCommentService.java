package com.m1k.goldenSpoon.board.model.service;

import java.util.List;

import com.m1k.goldenSpoon.board.model.dto.BoardComment;

public interface BoardCommentService {

	/** 댓글 조회
	 * @param boardNo
	 * @return
	 */
	List<BoardComment> selectBoardComment(int boardNo);

	/** 댓글 등록
	 * @param boardComment
	 * @return
	 */
	int enrollComment(BoardComment boardComment);

	/** 댓글 수정
	 * @param boardComment
	 * @return
	 */
	int updateComment(BoardComment boardComment);

	/** 댓글 삭제
	 * @param boardCommentNo
	 * @return
	 */
	int deleteComment(int boardCommentNo);


}
