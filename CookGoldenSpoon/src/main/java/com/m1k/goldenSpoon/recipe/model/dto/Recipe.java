package com.m1k.goldenSpoon.recipe.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Recipe {

	private int recipeNo;
	private String recipeTitle;
	private String recipeThumbnail;
	private String recipeIntro;
	private String recipeTip;
	private String recipeEnrollDate;
	private String recipeDelFl;
	private String recipeTime;
	private String recipePeople;
	private String recipeLevel;
	private String memberNickname;
	private double recipeStar;
	private int recipeLike;
	private String memberProfile;
	private String memberIntro;
	private int memberNo;
	private int recipeHits;
	
	private List<String> recipeTag;
	private List<RecipeStep> recipeStepList;
	private List<RecipeMaterial> recipeMaterialList;
	private List<RecipeComment> recipeCommentList;
	
}
