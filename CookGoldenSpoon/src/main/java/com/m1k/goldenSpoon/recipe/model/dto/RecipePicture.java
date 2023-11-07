package com.m1k.goldenSpoon.recipe.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class RecipePicture {

	private int recipeImageNo;
	private int recipeNo;
	private String recipeImage;
	private int recipeImageOrder;
}
