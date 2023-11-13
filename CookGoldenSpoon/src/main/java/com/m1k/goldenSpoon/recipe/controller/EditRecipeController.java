package com.m1k.goldenSpoon.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.m1k.goldenSpoon.recipe.model.service.EditRecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
@RequestMapping("editRecipe")
public class EditRecipeController {

	private final EditRecipeService service;
	
	@DeleteMapping("delete")
	public String deleteRecipe() {
		return null;
	}
}
