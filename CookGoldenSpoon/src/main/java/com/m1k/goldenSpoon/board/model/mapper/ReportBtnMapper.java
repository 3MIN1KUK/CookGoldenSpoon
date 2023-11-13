package com.m1k.goldenSpoon.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.board.model.dto.Board;

@Mapper

public interface ReportBtnMapper {

	/** 신고하기 버튼(통합)
	 * @param board
	 * @return
	 */
	int csPopupBtn(Board board);

}
