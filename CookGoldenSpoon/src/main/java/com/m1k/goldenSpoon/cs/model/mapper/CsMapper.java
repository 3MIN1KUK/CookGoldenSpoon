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

}
