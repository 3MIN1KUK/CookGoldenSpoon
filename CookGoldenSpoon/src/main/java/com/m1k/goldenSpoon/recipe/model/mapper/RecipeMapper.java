package com.m1k.goldenSpoon.recipe.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

@Mapper
public interface RecipeMapper {

	/** 레시피 상세 조회
	 * @param recipeNo
	 * @return recipe
	 */
	Recipe recipeDetail(int recipeNo);

	/** 레시피 글 수 전체 조회
	 * @return
	 */
	int listCount();

	/** 레시피 조회
	 * @param rowBounds
	 * @return
	 */
	List<Recipe> selectRecipe(RowBounds rowBounds);

}
