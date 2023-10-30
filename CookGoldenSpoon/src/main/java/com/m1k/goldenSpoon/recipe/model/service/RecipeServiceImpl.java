package com.m1k.goldenSpoon.recipe.model.service;

import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.mapper.RecipeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{

	private final RecipeMapper mapper;
	
	@Override
	public Recipe recipeDetail(int recipeNo) {
		return mapper.recipeDetail(recipeNo);
	}
}
