package com.m1k.goldenSpoon.recipe.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m1k.goldenSpoon.recipe.model.dto.RecipeComment;
import com.m1k.goldenSpoon.recipe.model.service.RecipeCommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecipeCommentController {

	private final RecipeCommentService service;
	
	// 레시피 비동기 조회
	@GetMapping(value = "recipeComment", produces = "application/json")
	public List<RecipeComment> selectRecipeComment(int recipeNo){
		return service.selectRecipeComment(recipeNo);
	}
	
	// 레시피 댓글 등록
    @PostMapping("recipeComment/enrollComment")
    public int enrollComment(@RequestBody RecipeComment recipeComment) {
    	return service.enrollComment(recipeComment);
    }
}
