package com.m1k.goldenSpoon.recipe.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.mapper.RecipeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{

	private final RecipeMapper mapper;
	
	@Override
	public Recipe enroll(Recipe recipe) {
		
		
		return mapper.enroll(recipe);
	}
	
	public Recipe recipeDetail(int recipeNo) {
		return mapper.recipeDetail(recipeNo);
	}
	
	@Override
	public Map<String, Object> selectRecipe(int cp) {
		int listCount = mapper.listCount();
		Pagination pagination = new Pagination(cp, listCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Recipe> recipeList = mapper.selectRecipe(rowBounds);
		Map<String,	Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);
		return map;
	}
	
	@Override
	public Map<String, Object> search(int cp, String inputSearch) {
		int listCount = mapper.listSearchCount(inputSearch);
		Pagination pagination = new Pagination(cp, listCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Recipe> recipeList = mapper.selectSearchRecipe(inputSearch, rowBounds);
		Map<String,	Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);
		return map;
	}
}
