package com.m1k.goldenSpoon.recipe.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
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
		if(loginMember == null) {
			ra.addFlashAttribute("message", "로그인 후 이용해주세요");
			return "redirect:/recipe/select?recipeNo=" + recipeNo;
		}
		Recipe recipe = recipeService.recipeDetail(recipeNo);
		model.addAttribute("recipe", recipe);
		return "recipe/edit/recipeUpdate";
	}
	
	// 레시피 수정
	@PostMapping("update")
	public String updateRecipe(Recipe recipe, String originRecipeVideo, @SessionAttribute("loginMember") Member loginMember, 
	    	@RequestParam("thumbnail") MultipartFile thumbnail,
	    	@RequestParam(value = "recipeTagName", required = false) List<String> recipeTagName,
	    	@RequestParam("recipeStepContent") List<String> recipeStepContent,
			@RequestParam("processImages") List<MultipartFile> recipeStepImage,
			@RequestParam("completeImages") List<MultipartFile> completeImages,
			@RequestParam("materialName") List<String> materialName,
			@RequestParam("recipeMaterialQuantity") List<String> recipeMaterialQuantity,
			String deleteCompleteOrder,String deleteThumbnail,
			RedirectAttributes ra) throws IllegalStateException, IOException {
		
		recipe.setMemberNo(loginMember.getMemberNo());
		
		int result = service.update(recipe, originRecipeVideo, thumbnail, recipeTagName, recipeStepContent,
    			recipeStepImage, completeImages, materialName, recipeMaterialQuantity, deleteCompleteOrder,
    			deleteThumbnail);
		
		if(result > 0) {
			ra.addFlashAttribute("message", "수정 성공");
		} else {
			ra.addFlashAttribute("message", "수정 실패");
		}
		return "redirect:/recipe/select/" + recipe.getRecipeNo();
	}
	
	
	
	// 레시피 삭제 
	@GetMapping("delete")
	public String deleteRecipe(int recipeNo, RedirectAttributes ra,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		if(loginMember == null) {
			ra.addFlashAttribute("message", "로그인 후 이용해주세요");
			return "redirect:/recipe/select?recipeNo=" + recipeNo;
		}
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
