package com.m1k.goldenSpoon.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.Report;

@Mapper

public interface ReportBtnMapper {

	/** 신고하기 버튼(통합)
	 * @param report
	 * @return
	 */
	int csPopupBtn(Report report);

}
