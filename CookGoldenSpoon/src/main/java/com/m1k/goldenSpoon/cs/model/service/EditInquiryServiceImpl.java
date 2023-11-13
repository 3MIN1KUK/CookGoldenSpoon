package com.m1k.goldenSpoon.cs.model.service;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m1k.goldenSpoon.cs.model.mapper.EditInquiryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
public class EditInquiryServiceImpl implements EditInquiryService{
	
	private final EditInquiryMapper mapper;
	
}
