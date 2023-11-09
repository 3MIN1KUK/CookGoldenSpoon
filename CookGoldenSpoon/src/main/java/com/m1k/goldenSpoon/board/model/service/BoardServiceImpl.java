package com.m1k.goldenSpoon.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.mapper.BoardMapper;
import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.cs.model.dto.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper mapper;
	
	@Override
	public Map<String, Object> selectAllBoard(int boardCode, int cp) {
		
		// 전체 글 수 조회
		int listCount = mapper.getBoardListCount(boardCode);

		Pagination pagination = new Pagination(cp, listCount, 14, 10);

		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Board> boardList = mapper.selectAllBoard(boardCode, rowBounds);
		Map<String, Object> map = new HashMap<>();
		map.put("boardList", boardList);
		map.put("pagination", pagination);
		return map;
	} 
	
	@Override
	public Board boardDetail(Map<String, Object> map) {
		return mapper.boardDetail(map);
	}
	
	// 게시글 조회 수 증가
	@Override
	public int updateBoardHits(int boardNo) {
		return mapper.updateBoardHits(boardNo);
	}
	
}
