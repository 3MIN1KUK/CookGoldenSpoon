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

	/** 게시글 부분만 수정
	 * @param board
	 * @return
	 */
	int updateBoard(Board board);

	/** 이미지 삭제
	 * @param map
	 * @return
	 */
	int imageDelete(Map<String, Object> map);

	/** 이미지 1개 수정
	 * @param img
	 * @return
	 */
	int updateBoardImg(BoardImg img);

	/** 이미지 하나 삽입
	 * @param img
	 */
	void boardImgInsert(BoardImg img);

}
