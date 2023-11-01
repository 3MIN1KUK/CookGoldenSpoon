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
	
	@Override
	public int like(Map<String, Object> paramMap) {
		int result = 0;
		if((Integer)(paramMap.get("check")) == 1) {
			result = mapper.deleteRecipeLike(paramMap);
		}
		else {
			result = mapper.insertRecipeLike(paramMap);
		}
		if(result == 0) return -1;
		return mapper.countRecipeLike((Integer)(paramMap.get("boardNo")));
	}
	
	@Override
	public int bookmark(Map<String, Object> paramMap) {
		int result = 0;
		if((Integer)(paramMap.get("check")) == 1) {
			result = mapper.deleteRecipeBookmark(paramMap);
		}
		else {
			result = mapper.insertRecipeBookmark(paramMap);
		}
		if(result == 0) return -1;
		return result;
	}
}
