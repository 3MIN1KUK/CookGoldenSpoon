package com.m1k.goldenSpoon.email.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m1k.goldenSpoon.email.model.service.EmailService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailController {

	private final EmailService service;
	
	
	@PostMapping("signup")
	public int signup(@RequestBody String email) {
		return service.sendEmail("signup", email);
		
	}
	
	@PostMapping("checkAuthKey")
	public int checkAuthKey(@RequestBody Map<String, Object> paramMap) {
		return service.checkAuthKey(paramMap);
	}
}
