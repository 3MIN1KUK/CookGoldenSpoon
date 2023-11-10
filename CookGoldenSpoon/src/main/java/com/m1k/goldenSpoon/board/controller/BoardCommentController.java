package com.m1k.goldenSpoon.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.m1k.goldenSpoon.board.model.dto.BoardComment;
import com.m1k.goldenSpoon.board.model.service.BoardCommentService;

import lombok.RequiredArgsConstructor;

@RestController // 응답만 하는 컨트롤러
@RequiredArgsConstructor
public class BoardCommentController {
	
	private final BoardCommentService service;
	
	/** 특정 게시글의 댓글 목록 조회
	 * @param boardNo
	 * @return
	 */
	@GetMapping(value="boardComment", produces = "application/json")
	public List<BoardComment> select(int boardNo){
		return service.select(boardNo);
	}
	
	/** 댓글 등록
	 * @param boardComment
	 * @return
	 */
	@PostMapping("boardComment")
	public int insert(@RequestBody BoardComment boardComment) {
		return service.insert(boardComment);
	}
		
	/** 댓글 수정
	 * @param boardComment
	 * @return
	 */
	@PutMapping("boardComment")
	public int update(@RequestBody BoardComment boardComment) {
		return service.update(boardComment);
	}
	
	/** 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	@DeleteMapping("boardComment")
	public int delete(@RequestBody int commentNo) {
		return service.delete(commentNo);
	}
	
}
