package com.m1k.goldenSpoon.cs.model.service;

import java.util.List;

import com.m1k.goldenSpoon.cs.model.dto.Notice;

public interface CsService {

	/** 공지사항 검색
	 * @param searchNotice
	 * @return noticeList
	 */
	List<Notice> noticeSelect(String searchNotice);

	/** 모든 공지사항 조회
	 * @return
	 */
	List<Notice> selectAllNotice();

}
