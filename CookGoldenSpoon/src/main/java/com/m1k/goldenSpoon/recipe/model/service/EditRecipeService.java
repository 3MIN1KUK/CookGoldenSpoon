package com.m1k.goldenSpoon.recipe.model.service;

import java.util.Map;

public interface EditRecipeService {

	/** 레시피 삭제
	 * @param map
	 * @return
	 */
	int deleteRecipe(Map<String, Object> map);

}
