package com.m1k.goldenSpoon.myPage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.myPage.model.mapper.MyPageMapper;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

@Service
public class MyPageServiceImpl implements MyPageService{

	@Autowired
	private MyPageMapper mapper;
	
	@Override
	public Member myPage(int memberNo) {
		return mapper.myPage(memberNo);
	}
	
	// 좋아요 누른 글 조회
	@Override
	public Map<String, Object> myPageLike(int memberNo, int cp) {
		int recipeListCount = mapper.getRecipeListCount(memberNo);
		Pagination pagination = new Pagination(cp, recipeListCount, 14, 7);
		int Offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int Limit = pagination.getLimit();
		RowBounds RowBounds = new RowBounds(Offset, Limit);
		List<Recipe> recipeList = mapper.recipeSelect(memberNo, RowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);
		return map;
	}
	
	// 즐겨찾기 누른 레시피 조회
	@Override
	public Map<String, Object> myPageBookmark(int memberNo, int cp) {
		int recipeListCount = mapper.getRecipeListCount(memberNo);
		Pagination pagination = new Pagination(cp, recipeListCount, 8, 7);
		int Offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int Limit = pagination.getLimit();
		RowBounds RowBounds = new RowBounds(Offset, Limit);
		List<Recipe> recipeList = mapper.recipeSelect(memberNo, RowBounds);
		Map<String, Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);
		return map;
	}
	
	// 내가 쓴 게시글 조회
	@Override
	public Map<String, Object> myPageBoard(int memberNo, int cp) {
		int boardListCount = mapper.getBoardListCount(memberNo);
		Pagination pagination = new Pagination(cp, boardListCount, 14, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds RowBounds = new RowBounds(offset, limit);
		List<Board> boardList = mapper.boardSelect(memberNo, RowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardList", boardList);
		map.put("pagination", pagination);
		return map;
	}

	// 내가 쓴 레시피 조회
	@Override
	public Map<String, Object> myPageRecipe(int memberNo, int cp) {
		int myRecipeListCount = mapper.getMyRecipeListCount(memberNo);
		Pagination pagination = new Pagination(cp, myRecipeListCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds RowBounds = new RowBounds(offset, limit);
		List<Recipe> recipeList = mapper.myRecipeSelect(memberNo, RowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);

		return map;
	}
}
