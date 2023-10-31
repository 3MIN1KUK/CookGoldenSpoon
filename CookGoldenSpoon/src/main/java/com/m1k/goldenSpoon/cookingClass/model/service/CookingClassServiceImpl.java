package com.m1k.goldenSpoon.cookingClass.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.cookingClass.model.mapper.CookingClassMapper;
import com.m1k.goldenSpoon.cs.model.dto.Notice;

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
