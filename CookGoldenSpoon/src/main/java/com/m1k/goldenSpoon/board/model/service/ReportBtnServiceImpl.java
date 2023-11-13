package com.m1k.goldenSpoon.board.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.mapper.ReportBtnMapper;
import com.m1k.goldenSpoon.myPage.model.dto.MyPagePwChange;

import lombok.RequiredArgsConstructor;

@Transactional(rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class ReportBtnServiceImpl implements ReportBtnService{
	
	private final ReportBtnMapper mapper;

	// 신고버튼
	@Override
	public int csPopupBtn(Board board) {
		return mapper.csPopupBtn(board);
	}
	
	
}
