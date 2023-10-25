package com.m1k.goldenSpoon.cookingClass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.m1k.goldenSpoon.cookingClass.model.service.CookingClassService;

@Controller
@RequestMapping("cookingClass")
public class CookingClassController {
	
	@Autowired
	private CookingClassService service;

	@GetMapping("cookingClassBoard")
	public String cookingClassBoard () {
		return "/cooking_class/board/cookingClassBoard";
	}
	
}