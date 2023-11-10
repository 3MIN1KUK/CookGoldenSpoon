package com.m1k.goldenSpoon.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m1k.goldenSpoon.board.model.dto.BoardComment;
import com.m1k.goldenSpoon.board.model.mapper.BoardCommentMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService {
	
	private final BoardCommentMapper mapper;
	
	// 댓글 목록 조회
	@Override
		public List<BoardComment> select(int boardNo) {
			return mapper.select(boardNo);
		}
	
	// 댓글 등록
	@Override
	public int insert(BoardComment boardComment) {
		return mapper.insert(boardComment);
	}
	
	// 댓글 수정
	@Override
	public int update(BoardComment boardComment) {
		return mapper.update(boardComment);
	}
	
	// 댓글 삭제
	@Override
	public int delete(int commentNo) {
		return mapper.delete(commentNo);
	}
	
	
}
