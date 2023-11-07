package com.m1k.goldenSpoon.board.model.service;

import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.board.model.mapper.EditBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditBoardServiceImpl implements EditBoardService{
	
	private final EditBoardMapper mapper;
}
