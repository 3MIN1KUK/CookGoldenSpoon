package com.m1k.goldenSpoon.cs.model.service;

import java.util.List;
import java.util.Map;

import com.m1k.goldenSpoon.cs.model.dto.Notice;

public interface CsService {

	/** 공지사항 검색
	 * @param searchNotice
	 * @return noticeList
	 */
	List<Notice> noticeSelect(String searchNotice);

	/** 모든 공지사항 조회
	 * @param cp 
	 * @param order 
	 * @return
	 */
	Map<String, Object> selectAllNotice(int cp, int order);

	/** 공지 상세조회
	 * @param noticeNo
	 * @return notice
	 */
	Notice noticeDetail(int noticeNo);

}
