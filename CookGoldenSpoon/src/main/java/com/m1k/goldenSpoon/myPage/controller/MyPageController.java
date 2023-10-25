package com.m1k.goldenSpoon.myPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.m1k.goldenSpoon.myPage.model.service.MyPageService;

@Controller
public class MyPageController {

	@Autowired
	private MyPageService service;
}
