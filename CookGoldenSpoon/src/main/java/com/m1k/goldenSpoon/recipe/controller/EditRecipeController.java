package com.m1k.goldenSpoon.recipe.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.service.EditRecipeService;
import com.m1k.goldenSpoon.recipe.model.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
@RequestMapping("editRecipe")
public class EditRecipeController {

	private final EditRecipeService service;
	private final RecipeService recipeService;
	
	// 레시피 수정 화면 전환
	@GetMapping("update")
	public String updateRecipe(@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra, int recipeNo, Model model) {
		Recipe recipe = recipeService.recipeDetail(recipeNo);
		model.addAttribute("recipe", recipe);
		return "recipe/recipeUpdate";
	}
	
	// 레시피 수정
	@PutMapping("update")
	public String updateRecipe() {
		return null;
	}
	
	
	
	// 레시피 삭제  ㅊ ㅁ
	@DeleteMapping("delete")
	public String deleteRecipe(int recipeNo, RedirectAttributes ra,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", loginMember.getMemberNo());
		map.put("recipeNo", recipeNo);
		int result = service.deleteRecipe(map);
		if(result > 0) {
			ra.addFlashAttribute("message", "레시피가 삭제되었습니다");
			return "redirect:/recipe/select";
		}
		ra.addFlashAttribute("message", "레시피 삭제 실패");
		return "redirect:" + recipeNo;
	}
	
}
