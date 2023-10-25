package com.m1k.goldenSpoon.cs.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.cs.model.dto.Notice;
import com.m1k.goldenSpoon.cs.model.mapper.CsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CsServiceImpl implements CsService{

	private final CsMapper mapper;
	
	@Override
	public List<Notice> noticeSelect(String searchNotice) {
		return mapper.noticeSelect(searchNotice);
	}
}
