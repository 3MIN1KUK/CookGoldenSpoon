package com.m1k.goldenSpoon.myPage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.myPage.model.mapper.MyPageMapper;

@Service
public class MyPageServiceImpl implements MyPageService{

	@Autowired
	private MyPageMapper mapper;
}
