package com.m1k.goldenSpoon.recipe.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.BoardImg;
import com.m1k.goldenSpoon.board.model.exception.BoardWriteException;
import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.common.utility.Util;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.mapper.RecipeMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{

	private final RecipeMapper mapper;
	
	@Value("${my.board.location}")
	private String folderPath;
	
	@Value("${my.board.webpath}")
	private String webPath;
	
	@Override
	public Recipe recipeDetail(int recipeNo) {
		return mapper.recipeDetail(recipeNo);
	}
	
	@Override
	public Map<String, Object> selectRecipe(int cp) {
		int listCount = mapper.listCount();
		Pagination pagination = new Pagination(cp, listCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Recipe> recipeList = mapper.selectRecipe(rowBounds);
		Map<String,	Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);
		return map;
	}
	
	@Override
	public Map<String, Object> search(int cp, String inputSearch) {
		int listCount = mapper.listSearchCount(inputSearch);
		Pagination pagination = new Pagination(cp, listCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Recipe> recipeList = mapper.selectSearchRecipe(inputSearch, rowBounds);
		Map<String,	Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);
		return map;
	}
	
	// 좋아요 처리
	@Override
	public int like(Map<String, Object> paramMap) {
		int result = 0;
		if((Integer)(paramMap.get("check")) == 1) {
			result = mapper.deleteRecipeLike(paramMap);
		}
		else {
			result = mapper.insertRecipeLike(paramMap);
		}
		if(result == 0) return -1;
		return mapper.countRecipeLike((Integer)(paramMap.get("boardNo")));
	}
	@Override
	public int likeCheck(Map<String, Integer> map) {
		return mapper.likeCheck(map);
	}
	
	// 즐겨찾기 처리
	@Override
	public int bookmark(Map<String, Object> paramMap) {
		int result = 0;
		if((Integer)(paramMap.get("check")) == 1) {
			result = mapper.deleteRecipeBookmark(paramMap);
		}
		else {
			result = mapper.insertRecipeBookmark(paramMap);
		}
		if(result == 0) return -1;
		return result;
	}
	
	@Override
	public int bookmarkCheck(Map<String, Integer> map) {
		return mapper.bookmarkCheck(map);
	}
	
	// 좋아요 순 레시피 조회
	@Override
	public Map<String, Object> popularRecipe(int cp) {
		Pagination pagination = new Pagination(cp, 40, 8, 1);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Recipe> recipeList = mapper.selectPopularRecipe(rowBounds);
		Map<String,	Object> map = new HashMap<>();
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);
		return map;
	}
	
	// 별점 기능
	@Override
	public int stars(Map<String, Object> paramMap) {
		int result = 0;
		if((Integer)(paramMap.get("check")) == 0) {
			result = mapper.insertRecipeStar(paramMap);
		} else {
			result = mapper.updateRecipeStar(paramMap);
		}
			
		return result;
	}
	
	// 별점 수 체크
	@Override
	public int starsCheck(Map<String, Integer> map) {
//		if(mapper.countStarsCheck(map) == 0) {
//			return 0;
//		}
		if(mapper.starsCheck(map) == null) {
			return 0;
		} else {
			return Integer.parseInt(String.valueOf(mapper.starsCheck(map)));
		}
	}
	
	
	// 레시피 등록
	@Override
	public int enroll(Board board, List<MultipartFile> images) throws IllegalStateException, IOException {
		int result = mapper.insertBoard(board);
		if(result == 0) return 0; 
		int boardNo = board.getBoardNo();
		List<BoardImg> uploadList = new ArrayList<>();
		for(int i = 0 ; i<images.size(); i++) {
			if(images.get(i).getSize() > 0) {
				BoardImg img = new BoardImg();
				img.setBoardNo(boardNo); 
				img.setBoardImageOrder(i);
				img.setBoardImageName( images.get(i).getOriginalFilename() ); 
				img.setBoardImage(webPath);
				img.setBoardImageRename(Util.fileRename( images.get(i).getOriginalFilename() ));
				img.setUploadFile(images.get(i));
				uploadList.add(img);
			} // if문 끝
		}// for문 끝
		if(uploadList.isEmpty()) {
			return boardNo;
		}
		result = mapper.insertUploadList(uploadList);
		if(result == uploadList.size()) {
			for(BoardImg img : uploadList) {
				img.getUploadFile().transferTo(new File(folderPath + img.getBoardImageRename()));
			}
		} else {
			throw new BoardWriteException("파일 정보 DB 삽입 실패");
		}
		return boardNo;
	}
}
