package com.m1k.goldenSpoon.recipe.model.service;

import java.util.List;

import com.m1k.goldenSpoon.recipe.model.dto.RecipeComment;

public interface RecipeCommentService {

	/** 레시피 댓글 등록
	 * @param recipeComment
	 * @return
	 */
	int enrollComment(RecipeComment recipeComment);

	/** 댓글 비동기 조회
	 * @param recipeNo
	 * @return
	 */
	List<RecipeComment> selectRecipeComment(int recipeNo);

}
