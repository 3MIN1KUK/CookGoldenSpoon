package com.m1k.goldenSpoon.recipe.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecipeComment {

	private int recipeParentNo;
	private String recipeCommentEnrollDate;
	private String recipeCommentContent;
	private int recipeCommentNo;
	private int recipeNo;
	private int memberNo;
	private String memberNickname;
	private String memberProfile;
	private String recipeCommentDelFl;
}
