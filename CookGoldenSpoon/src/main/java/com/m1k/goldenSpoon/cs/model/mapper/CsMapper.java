package com.m1k.goldenSpoon.cs.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.cs.model.dto.Notice;

@Mapper
public interface CsMapper {

	/** 공지사항 검색
	 * @param searchNotice
	 * @param rowBounds 
	 * @return noticeList
	 */
	List<Notice> noticeSelect(String searchNotice, RowBounds rowBounds);

	/** 모든 공지사항 검색
	 * @param order 
	 * @param rowBounds 
	 * @return
	 */
	List<Notice> selectAllNotice(int order, RowBounds rowBounds);

	/** 공지 상세조회
	 * @param noticeNo
	 * @return notice
	 */
	Notice noticeDetail(int noticeNo);

	/** 공지사항 글 수 조회
	 * @return
	 */
	int getNoticeListCount();

}
