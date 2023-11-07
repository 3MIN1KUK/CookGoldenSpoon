package com.m1k.goldenSpoon.main.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.m1k.goldenSpoon.recipe.model.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final RecipeService service;
	
	@RequestMapping("/")
	public String mainPage(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		Map<String, Object> map = service.popularRecipe(cp);
		model.addAttribute("map", map);
		
		return "index";
	}
	
	
	/** 로그인 하지 않고 로그인 전용 페이지 접근 시
	 * @param ra
	 * @return
	 */
	@GetMapping("loginError")
	public String loginError(RedirectAttributes ra) {
		
		ra.addFlashAttribute("message", " 로그인 후 이용 해주세요");
		return "redirect:/";
	}
	
	
	/** 관리자 권한이 없는 상태에서 관리자 페이지 접근 시
	 * @param ra
	 * @return
	 */
	@GetMapping("adminError")
	public String adminError(RedirectAttributes ra) {
		
		ra.addFlashAttribute("message", " 정상 접근이 아닙니다");
		return "redirect:/";
	}
	
	
	
}
