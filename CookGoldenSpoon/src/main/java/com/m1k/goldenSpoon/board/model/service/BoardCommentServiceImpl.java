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
	
	@Override
	public List<BoardComment> selectBoardComment(int boardNo) {
		return mapper.selectBoardComment(boardNo);
	}
	
	@Override
	public int enrollComment(BoardComment boardComment) {
		return mapper.enrollComment(boardComment);
	}
	
	@Override
	public int updateComment(BoardComment boardComment) {
		return mapper.updateComment(boardComment);
	}
	
	@Override
	public int deleteComment(int boardCommentNo) {
		return mapper.deleteComment(boardCommentNo);
	}
	
}
