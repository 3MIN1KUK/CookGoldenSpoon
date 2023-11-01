package com.m1k.goldenSpoon.recipe.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecipeStep {

	private String recipeStepContent;
	private int recipeStepOrder;
	private String recipeStepImage;
	
}
