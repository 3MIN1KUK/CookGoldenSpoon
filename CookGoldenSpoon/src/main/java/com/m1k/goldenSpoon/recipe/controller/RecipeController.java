package com.m1k.goldenSpoon.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.m1k.goldenSpoon.recipe.model.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("recipe")
@RequiredArgsConstructor
public class RecipeController {
	
	private final RecipeService service;
	
	@GetMapping("search")
	public String search(String inputSearch, Model model) {
		model.addAttribute("inputSearch", inputSearch);
		return "recipe/select/";
	}


}
