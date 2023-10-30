package com.m1k.goldenSpoon.recipe.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

@Mapper
public interface RecipeMapper {

	/** 레시피 상세 조회
	 * @param recipeNo
	 * @return recipe
	 */
	Recipe recipeDetail(int recipeNo);

}
