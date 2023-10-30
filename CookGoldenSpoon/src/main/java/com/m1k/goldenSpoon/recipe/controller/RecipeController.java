package com.m1k.goldenSpoon.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("recipe")
@RequiredArgsConstructor
public class RecipeController {
	
	private final RecipeService service;
	
	@GetMapping("select")
	public String search(String inputSearch, Model model) {
		model.addAttribute("inputSearch", inputSearch);
		return "recipe/select/";
	}
	
	@GetMapping("select/{recipeNo:[0-9]+}")
	public String recipeDetail(@PathVariable("recipeNo") int recipeNo, Model model) {
//		Recipe recipe = service.recipeDetail(recipeNo);
//		model.addAttribute(recipe);
		return "recipe/select/recipeDetail";
	}

    
    @GetMapping("enroll")
    public String enroll() {
        return "recipe/enroll/enroll_recipe";
    }

}
