package com.m1k.goldenSpoon.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.board.model.dto.BoardComment;

@Mapper
public interface BoardCommentMapper {

	/** 댓글 조회
	 * @param boardNo
	 * @return
	 */
	List<BoardComment> selectBoardComment(int boardNo);

	/** 댓글 수정
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
