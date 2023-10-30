package com.m1k.goldenSpoon.recipe.model.service;

import java.util.Map;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

public interface RecipeService {

	/** 레시피 상세조회
	 * @param recipeNo
	 * @return recipe
	 */
	Recipe recipeDetail(int recipeNo);

	/** 레시피 조회
	 * @return
	 */
	Map<String, Object> selectRecipe(int cp);

	/** 레시피 검색
	 * @param cp 
	 * @param inputSearch
	 * @return
	 */
	Map<String, Object> search(int cp, String inputSearch);

}
