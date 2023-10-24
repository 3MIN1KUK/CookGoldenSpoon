package com.m1k.goldenSpoon.cookingClass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cookingClass")
public class CookingClassController {

	@GetMapping("cookingClassBoard")
	public String cookingClassBoard () {
		return "/cooking_class/board/cookingClassBoard";
	}
	
}
