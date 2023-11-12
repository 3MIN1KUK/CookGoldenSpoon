package com.m1k.goldenSpoon.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m1k.goldenSpoon.board.model.dto.BoardComment;
import com.m1k.goldenSpoon.board.model.service.BoardCommentService;

import lombok.RequiredArgsConstructor;

@RestController // 응답만 하는 컨트롤러
@RequiredArgsConstructor
@RequestMapping("boardComment")
public class BoardCommentController {
	
	private final BoardCommentService service;
	
	/** 특정 게시글의 댓글 목록 조회
	 * @param boardNo
	 * @return
	 */
	@GetMapping(value="select", produces = "application/json")
	public List<BoardComment> selectBoardComment(int boardNo){
		return service.selectBoardComment(boardNo);
	}
	
	/** 댓글 등록
	 * @param boardComment
	 * @return
	 */
	@PostMapping("enrollComment")
	public int enrollComment(@RequestBody BoardComment boardComment) {
		return service.enrollComment(boardComment);
	}
		
	/** 댓글 수정
	 * @param boardComment
	 * @return
	 */
	@PutMapping("updateComment")
	public int updateComment(@RequestBody BoardComment boardComment) {
		return service.updateComment(boardComment);
	}
	
	/** 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	@DeleteMapping("deleteComment")
	public int deleteComment(@RequestBody int boardCommentNo) {
		return service.deleteComment(boardCommentNo);
	}
	
}
