package com.m1k.goldenSpoon.recipe.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m1k.goldenSpoon.recipe.model.mapper.EditRecipeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EditRecipeServiceImpl implements EditRecipeService{
	
	private final EditRecipeMapper mapper;

	@Override
	public int deleteRecipe(Map<String, Object> map) {
		return mapper.deleteRecipe(map);
	}
	
}
