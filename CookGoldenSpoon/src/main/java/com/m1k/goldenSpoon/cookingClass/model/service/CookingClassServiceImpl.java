package com.m1k.goldenSpoon.cookingClass.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.cookingClass.model.mapper.CookingClassMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CookingClassServiceImpl implements CookingClassService{

	private final CookingClassMapper mapper;
	
	@Override
	public Map<String, Object> selectAllCommuntry(int com, int order) {
		return null;
	}

	
}
