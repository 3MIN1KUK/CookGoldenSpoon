package com.m1k.goldenSpoon.myPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.m1k.goldenSpoon.myPage.model.service.MyPageService;
import com.m1k.goldenSpoon.recipe.model.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("my_page")
@RequiredArgsConstructor
public class MyPageController {

	
	private final MyPageService service;
	
	/*
	 * @PostMapping("teacher") public String teacher() { return
	 * "my_page/teacher/cookingclass_teacher"; }
	 */
	
}
