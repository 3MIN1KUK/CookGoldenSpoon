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

import jakarta.mail.internet.ParseException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper mapper;
	
	@Override
	public Map<String, Object> selectAllBoard(int boardCode, int cp) throws ParseException {
		
		// 전체 글 수 조회
		int listCount = mapper.getBoardListCount(boardCode);

		Pagination pagination = new Pagination(cp, listCount, 14, 10);

		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Board> boardList = mapper.selectAllBoard(boardCode, rowBounds);
		String boardType = mapper.selectBoardType(boardCode);
		Map<String, Object> map = new HashMap<>();
		map.put("boardType", boardType);
		map.put("boardList", boardList);
		map.put("pagination", pagination);
		return map;
	} 
	
	// 검색창 조회
	@Override
	public Map<String, Object> searchAllBoard(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.searchListCount(paramMap);
		
		Pagination pagination = new Pagination(cp, listCount, 14, 10);
		
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Board> boardList = mapper.searchAllBoard(paramMap, rowBounds);
		int boardCode = (int) paramMap.get("boardCode");
		String boardType = mapper.selectBoardType(boardCode);

		Map<String, Object> map = new HashMap<>();
		map.put("boardType", boardType);
		map.put("boardList", boardList);
		map.put("pagination", pagination);
		return map;
	}
	
	
	
	
	// 게시글 상세조회
	@Override
	public Board boardDetail(Map<String, Object> map) {
		return mapper.boardDetail(map);
	}
	
	// 게시글 조회 수 증가
	@Override
	public int updateBoardHits(int boardNo){
		return mapper.updateBoardHits(boardNo) ;
	}
	
	

	
	
	
	
}
