package com.m1k.goldenSpoon.recipe.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.recipe.model.dto.RecipeComment;

@Mapper
public interface RecipeCommentMapper {

	/** 레시피 댓글 등록
	 * @param recipeComment
	 * @return
	 */
	int enrollComment(RecipeComment recipeComment);

	/** 레시피 비동기 조회
	 * @param recipeNo
	 * @return
	 */
	List<RecipeComment> selectRecipeComment(int recipeNo);

	/** 댓글 삭제
	 * @param recipeNo
	 * @return
	 */
	int deleteComment(int recipeCommentNo);

	/** 댓글 수정
	 * @param recipeComment
	 * @return
	 */
	int updateComment(RecipeComment recipeComment);

}
