package com.m1k.goldenSpoon.recipe.model.dto;

import org.springframework.web.multipart.MultipartFile;

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
	private String recipeImageRename;
	private String recipeImageName;
	
	private MultipartFile uploadFile;
}
