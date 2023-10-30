package com.m1k.goldenSpoon.recipe.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Recipe {

	private int recipeNo;
	private String recipeName;
	private String recipeBrief;
	private String writer;
	private String ingredient;
	private String howToCook;
	private String tips;
	private double stars;
	private String thumbnail;
	private String hashTags;
	private String stepPhotos;
	private String endPhotos;
	private String mainPhotos;
	private String photos;
}
