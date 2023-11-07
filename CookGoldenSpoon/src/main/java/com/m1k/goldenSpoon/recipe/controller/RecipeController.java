package com.m1k.goldenSpoon.recipe.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.service.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("recipe")
@SessionAttributes({"loginMember"})
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
	public String recipeDetail(@PathVariable("recipeNo") int recipeNo, Model model, @SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra) {
		Recipe recipe = service.recipeDetail(recipeNo);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("recipeNo", recipeNo);
		
		String path = null;
		
		if(recipe != null) {
			model.addAttribute("recipe", recipe);
			
			path = "recipe/select/recipeDetail";
			
			if(loginMember != null) {
				map.put("memberNo", loginMember.getMemberNo());
				int likeCheck = service.likeCheck(map);
				int bookmarkCheck = service.bookmarkCheck(map);
				int recipeStar = service.starsCheck(map);
				
				if(recipeStar > 0) {
					model.addAttribute("recipeStar", recipeStar);
				} else {
					model.addAttribute("recipeStar", 0);
				}
				if(likeCheck == 1) model.addAttribute("likeCheck", "on");
				if(bookmarkCheck == 1) model.addAttribute("bookmarkCheck", "on");
			}
		} else {
			path = "redirect:/recipe/select";
			ra.addFlashAttribute("message", "해당 레시피가 존재하지 않습니다");
		}
		return path;
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
    
    @PostMapping("stars")
    @ResponseBody
    public int stars(@RequestBody Map<String, Object> paramMap, @SessionAttribute("loginMember") Member loginMember) {
    	paramMap.put("memberNo", loginMember.getMemberNo());
    	return service.stars(paramMap);
    }
}
