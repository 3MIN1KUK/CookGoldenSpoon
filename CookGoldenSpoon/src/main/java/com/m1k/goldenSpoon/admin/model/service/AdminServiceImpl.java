package com.m1k.goldenSpoon.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.m1k.goldenSpoon.admin.model.mapper.AdminMapper;
import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final AdminMapper mapper;
	
	// 회원 검색
	@Override
	public Map<String, Object> selectMember( int cp) {
		
		
		int listCount = mapper.selectListCount();
		
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> listMember = mapper.selectMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("listMember", listMember);
		map.put("pagination", pagination);
		
		return map;
	}
	
	// 회원 검색
	@Override
	public Map<String, Object> searchMember(Map<String, Object> paramMap, int cp) {
		
		
		int listCount = mapper.searchListCount(paramMap);
		
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> listMember = mapper.searchMember(paramMap, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("listMember", listMember);
		map.put("pagination", pagination);
		
		return map;
	}
	
	// 권한 변경
	@Override
	public int changeAuthority(Member member) {
		return mapper.changeAuthority(member);
	}
	
	
	@Override
	public Member memberDetail(int memberNo) {
		return mapper.memberDetail(memberNo);
	}
	
	
	// 레시피 검색
	@Override
	public Map<String, Object> recipeResult(Recipe searchRecipe, int cp) {
		
		
		List<Integer> memberNos= mapper.getMemberNos(searchRecipe.getMemberNickname());
		if (searchRecipe.getMemberNo() != 0) {
			memberNos.clear();
			memberNos.add(searchRecipe.getMemberNo());
		}
		Map<String, Object> map = new HashMap<>();
		
		map.put("memberNos", memberNos);
		map.put("searchRecipe", searchRecipe);
		
		
		int myRecipeListCount = mapper.recipeListCount(map);
		Pagination pagination = new Pagination(cp, myRecipeListCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds RowBounds = new RowBounds(offset, limit);
		List<Recipe> recipeList = mapper.recipeSelect(map, RowBounds);
		
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);

		return map;
	}
	
	// 게시글 검색
	@Override
	public Map<String, Object> boardResult(Board searchBoard, int cp) {
		
		List<Integer> memberNos= mapper.getMemberNos(searchBoard.getMemberNickname());
		if (searchBoard.getMemberNo() != 0) {
			memberNos.clear();
			memberNos.add(searchBoard.getMemberNo());
		}
		Map<String, Object> map = new HashMap<>();
		
		map.put("memberNos", memberNos);
		map.put("searchBoard", searchBoard);
		
		int boardListCount = mapper.boardListCount(map); 
		
		Pagination pagination = new Pagination(cp, boardListCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds RowBounds = new RowBounds(offset, limit);
		
		List<Board> boardList = mapper.boardResult(map, RowBounds);
		
		map.put("boardList", boardList);
		map.put("pagination", pagination);
		
		
		return map;
	}
	
	
}
