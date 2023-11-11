package com.m1k.goldenSpoon.recipe.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.recipe.model.dto.RecipeComment;
import com.m1k.goldenSpoon.recipe.model.mapper.RecipeCommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeCommentServiceImpl implements RecipeCommentService{
	
	private final RecipeCommentMapper mapper;

	// 댓글 등록
	@Override
	public int enrollComment(RecipeComment recipeComment) {
		return mapper.enrollComment(recipeComment);
	}
	
	// 댓글 목록 조회
	@Override
	public List<RecipeComment> selectRecipeComment(int recipeNo) {
		return mapper.selectRecipeComment(recipeNo);
	}
	
	// 댓글 삭제
	@Override
	public int deleteComment(int recipeCommentNo) {
		return mapper.deleteComment(recipeCommentNo);
	}
}
