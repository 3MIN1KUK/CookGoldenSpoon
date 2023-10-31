package com.m1k.goldenSpoon.cookingClass.model.service;

import java.util.Map;

public interface CookingClassService {
	
	/** 커뮤니티 게시판
	 * @param com
	 * @param order
	 * @return
	 */
	Map<String, Object> selectAllCommuntry(int com, int order);


}
