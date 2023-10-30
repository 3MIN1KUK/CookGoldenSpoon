package com.m1k.goldenSpoon.recipe.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

@Mapper
public interface RecipeMapper {

	Recipe enroll(Recipe recipe);

}
