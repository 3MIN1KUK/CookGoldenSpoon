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

	@Override
	public int enrollComment(RecipeComment recipeComment) {
		return mapper.enrollComment(recipeComment);
	}
	
	@Override
	public List<RecipeComment> selectRecipeComment(int recipeNo) {
		return mapper.selectRecipeComment(recipeNo);
	}
}
