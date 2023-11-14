package com.m1k.goldenSpoon.board.model.service;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.Report;
import com.m1k.goldenSpoon.myPage.model.dto.MyPagePwChange;

public interface ReportBtnService {

	/** 신고 버튼(통합)
	 * @param report
	 * @return
	 */
	int csPopupBtn(Report report);


}
