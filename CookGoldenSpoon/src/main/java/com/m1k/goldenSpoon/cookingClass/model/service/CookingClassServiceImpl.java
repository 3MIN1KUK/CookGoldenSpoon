package com.m1k.goldenSpoon.cookingClass.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.cookingClass.model.mapper.CookingClassMapper;

@Service
public class CookingClassServiceImpl implements CookingClassService{

	@Autowired
	private CookingClassMapper mapper;
}
