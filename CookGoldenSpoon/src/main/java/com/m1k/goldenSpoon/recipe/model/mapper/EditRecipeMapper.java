package com.m1k.goldenSpoon.recipe.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EditRecipeMapper {

	/** 게시글 삭제
	 * @param map
	 * @return
	 */
	int deleteRecipe(Map<String, Object> map);

}
