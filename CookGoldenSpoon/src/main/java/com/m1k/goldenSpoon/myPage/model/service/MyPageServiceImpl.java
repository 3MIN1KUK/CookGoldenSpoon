package com.m1k.goldenSpoon.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.common.utility.Util;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.myPage.model.dto.MyPagePwChange;
import com.m1k.goldenSpoon.myPage.model.mapper.MyPageMapper;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{

	private final MyPageMapper mapper;
	private final BCryptPasswordEncoder bcrypt;
	
	@Value("${my.member.webpath}")
	private String webpath;
	
	@Value("${my.member.location}")
	private String folderPath;
	
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
	
	@Override
	public Map<String, Object> myPageBookmarkSearch(int memberNo, int cp, int orderBy, String inputSearch) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputSearch", inputSearch);
		paramMap.put("orderBy", orderBy);
		paramMap.put("memberNo", memberNo);
		
		int recipeListCount = 0;
		if(orderBy == 5) {
			recipeListCount = mapper.getMyPageBookmarkWriterListCount(paramMap);
		} else {
			recipeListCount = mapper.getMyPageBookmarkSearchListCount(paramMap);
		}
		Pagination pagination = new Pagination(cp, recipeListCount, 8, 7);
		int Offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int Limit = pagination.getLimit();
		RowBounds RowBounds = new RowBounds(Offset, Limit);
		
		List<Recipe> recipeList = null;
		if(orderBy == 5) {
			recipeList = mapper.myPageBookmarkWriterSelect(paramMap, RowBounds);
		} else {
			recipeList = mapper.myPageBookmarkSearchSelect(paramMap, RowBounds);
		}
		
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

	@Override
	public Map<String, Object> myPageBoardSearch(int memberNo, int cp, String inputSearch) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputSearch", inputSearch);
		paramMap.put("memberNo", memberNo);
		
		int boardListCount = mapper.getMyPageBoardSearchListCount(paramMap);
		
		Pagination pagination = new Pagination(cp, boardListCount, 14, 10);
		int Offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int Limit = pagination.getLimit();
		RowBounds RowBounds = new RowBounds(Offset, Limit);
		
		List<Board> boardList = mapper.getMyPageBoardSearchSelect(paramMap, RowBounds);
		
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

	// 내가 쓴 레시피 중에서 검색
	@Override
	public Map<String, Object> myPageRecipeSearch(int memberNo, int cp, String inputSearch, int orderBy) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputSearch", inputSearch);
		paramMap.put("orderBy", orderBy);
		paramMap.put("memberNo", memberNo);
		
		int recipeListCount = mapper.getMyPageRecipeSearchListCount(paramMap);
		
		Pagination pagination = new Pagination(cp, recipeListCount, 8, 7);
		int Offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int Limit = pagination.getLimit();
		RowBounds RowBounds = new RowBounds(Offset, Limit);
		
		List<Recipe> recipeList = mapper.myPageRecipeSearchSelect(paramMap, RowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);
		return map;
	}

	// 비동기 프로필 이미지 변경
	@Override
	public int myPageEditProfile(MultipartFile memberProfile, Member loginMember) throws IllegalStateException, IOException {
		
		String backup = loginMember.getMemberProfile();
		
		String rename = null;
		
		if(memberProfile != null) {
			rename = Util.fileRename(memberProfile.getOriginalFilename());
			
			loginMember.setMemberProfile(webpath + rename);
		} else {
			loginMember.setMemberProfile(null);
		}
		
		int result = mapper.myPageEditProfile(loginMember);
		
		if(result > 0) {
			if(memberProfile != null) {
				memberProfile.transferTo(new File(folderPath + rename));
			}
		} else {
			loginMember.setMemberProfile(backup);
		}
		return result;
	}
	

	// 닉네임 유효성 검사
	@Override
	public int myPageValidation(String memberNickname) {
		return mapper.myPageValidation(memberNickname);
	}
	
	// 내 정보 수정
	@Override
	public int myPageEdit(Member loginMember) {
		return mapper.myPageEdit(loginMember);
	}
	
	// 팝업 비밀번호 변경
	@Override
	public int myPageEditPw(MyPagePwChange pwChange) {
		
		String memberPw = mapper.getMemberPw(pwChange);
		
		if(!bcrypt.matches(pwChange.getCurPassword(), memberPw)) {
			return -1;
		}
		pwChange.setNewPassword( bcrypt.encode(pwChange.getNewPassword()) );
		return mapper.changePw(pwChange);
	}
	
	// 탈퇴
	@Override
	public int myPageSecession(int memberNo) {
		return mapper.myPageSecession(memberNo);
	}
}
