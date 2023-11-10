package com.m1k.goldenSpoon.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.BoardImg;

@Mapper
public interface EditBoardMapper {

	/** 게시글 삭제
	 * @param paramMap
	 * @return
	 */
	int deleteBoard(Map<String, Integer> paramMap);

	/** 게시글 작성 (사진 없음)
	 * @param board
	 * @return
	 */
	int insertBoard(Board board);

	/** 게시글 이미지 정보 일괄 삽입
	 * @param uploadList
	 * @return
	 */
	int insertUploadList(List<BoardImg> uploadList);

}
