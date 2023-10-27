package com.m1k.goldenSpoon.cs.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.cs.model.dto.Notice;

@Mapper
public interface CsMapper {

	/** 공지사항 검색
	 * @param searchNotice
	 * @return noticeList
	 */
	List<Notice> noticeSelect(String searchNotice);

	/** 모든 공지사항 검색
	 * @return
	 */
	List<Notice> selectAllNotice();

	/** 공지 상세조회
	 * @param noticeNo
	 * @return notice
	 */
	Notice noticeDetail(int noticeNo);

}
