package com.m1k.goldenSpoon.recipe.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("recipe")
@RequiredArgsConstructor
public class RecipeController {
	
	private final RecipeService service;
	
	@GetMapping("select")
	public String select(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		Map<String, Object> map = service.selectRecipe(cp);
		model.addAttribute("map", map);
		return "recipe/select/recipe";
	}
	
	@GetMapping("select/search")
	public String search(String inputSearch, Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		Map<String, Object> map = service.search(cp, inputSearch);
		model.addAttribute("map", map);
		model.addAttribute("inputSearch", inputSearch);
		return "recipe/select/recipe";
	}
	
	@GetMapping("select/{recipeNo:[0-9]+}")
	public String recipeDetail(@PathVariable("recipeNo") int recipeNo, Model model) {
		Recipe recipe = service.recipeDetail(recipeNo);
		model.addAttribute("recipe", recipe);
		return "recipe/select/recipeDetail";
	}

    
    @GetMapping("enroll")
    public String enroll(Recipe recipe, Model model) {
    	
//    	Recipe inputRecipe = service.enroll(recipe);
    	
    	
    	
        return "recipe/enroll/enroll_recipe";
    }

    @PostMapping("like")
    @ResponseBody
    public int like(@RequestBody Map<String, Object> paramMap, @SessionAttribute("loginMember") Member loginMember) {
    	
    	paramMap.put("memberNo", loginMember.getMemberNo());
    	return service.like(paramMap);
    }
    
    @PostMapping("bookmark")
    @ResponseBody
    public int bookmark(@RequestBody Map<String, Object> paramMap, @SessionAttribute("loginMember") Member loginMember) {
    	paramMap.put("memberNo", loginMember.getMemberNo());
    	return service.bookmark(paramMap);
    }
}
