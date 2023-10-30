package com.m1k.goldenSpoon.recipe.model.service;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

public interface RecipeService {

	/** 레시피 상세조회
	 * @param recipeNo
	 * @return recipe
	 */
	Recipe recipeDetail(int recipeNo);

}
