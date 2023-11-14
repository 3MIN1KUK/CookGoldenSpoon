package com.m1k.goldenSpoon.cs.model.service;

import java.util.Map;

import com.m1k.goldenSpoon.cs.model.dto.Notice;

public interface CsService {

	/** 공지사항 검색
	 * @param cp 
	 * @param searchNotice
	 * @return noticeList
	 */
	Map<String, Object> noticeSelect(int cp, String searchNotice);

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

	/** 공지사항 조회수
	 * @param noticeNo
	 * @return
	 */
	int updateCsHits(int noticeNo);


}
