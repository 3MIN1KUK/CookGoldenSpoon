package com.m1k.goldenSpoon.recipe.model.exception;

public class RecipeUpdateException extends RuntimeException{
	
	public RecipeUpdateException() {
		super("게시글 수정 중 예외 발생");
	}

	public RecipeUpdateException(String message) {
		super(message);
	}
}