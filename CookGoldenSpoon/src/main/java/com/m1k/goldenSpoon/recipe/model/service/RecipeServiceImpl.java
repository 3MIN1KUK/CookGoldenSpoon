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
	
	@Override
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
	
	// 좋아요 처리
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
	public int likeCheck(Map<String, Integer> map) {
		return mapper.likeCheck(map);
	}
	
	// 즐겨찾기 처리
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
	
	@Override
	public int bookmarkCheck(Map<String, Integer> map) {
		return mapper.bookmarkCheck(map);
	}
	
	// 좋아요 순 레시피 조회
	@Override
	public Map<String, Object> popularRecipe(int cp) {
		Pagination pagination = new Pagination(cp, 40, 8, 1);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Recipe> recipeList = mapper.selectPopularRecipe(rowBounds);
		Map<String,	Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);
		return map;
	}
	
	// 별점 기능
	@Override
	public int stars(Map<String, Object> paramMap) {
		int result = 0;
		if((Integer)(paramMap.get("starsCheck")) == 0) {
			result = mapper.insertRecipeStar(paramMap);
		} else {
			result = mapper.updateRecipeStar(paramMap);
		}
			
		return result;
	}
	
	// 별점 수 체크
	@Override
	public int starsCheck(Map<String, Integer> map) {
		if(mapper.countStarsCheck(map) == 0) {
			return 0;
		}
		
		return mapper.starsCheck(map);
	}
}
