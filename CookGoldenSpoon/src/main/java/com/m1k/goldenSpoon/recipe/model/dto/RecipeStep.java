package com.m1k.goldenSpoon.recipe.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecipeStep {
	
	private int recipeStepNo;
	private int recipeNo;
	private String recipeStepContent;
	private int recipeStepOrder;
	private String recipeStepImage;
	private String recipeStepImageRename;
	private String recipeStepImageName;
	
	private MultipartFile uploadFile;
}
