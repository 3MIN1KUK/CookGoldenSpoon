package com.m1k.goldenSpoon.recipe.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

public interface RecipeService {


	
	/** 레시피 상세조회
	 * @param recipeNo
	 * @return recipe
	 */
	Recipe recipeDetail(int recipeNo);

	/** 레시피 조회
	 * @return
	 */
	Map<String, Object> selectRecipe(int cp);


	/** 레시피 검색
	 * @param cp 
	 * @param inputSearch
	 * @return
	 */
	Map<String, Object> search(int cp, String inputSearch);


	/** 좋아요 처리
	 * @param paramMap
	 * @return
	 */
	int like(Map<String, Object> paramMap);

	/** 북마크처리
	 * @param paramMap
	 * @return
	 */
	int bookmark(Map<String, Object> paramMap);


	/** 좋아요 확인
	 * @param map
	 * @return
	 */
	int likeCheck(Map<String, Integer> map);


	/** 즐겨찾기 확인
	 * @param map
	 * @return
	 */
	int bookmarkCheck(Map<String, Integer> map);


	/** 메인 페이지 인기 레시피
	 * @return
	 */
	Map<String, Object> popularRecipe(int cp);


	/** 별점 기능
	 * @param paramMap
	 * @return
	 */
	int stars(Map<String, Object> paramMap);


	/** 별점 수 체크
	 * @param map
	 * @return
	 */
	int starsCheck(Map<String, Integer> map);


	int enroll(Board board, List<MultipartFile> images) throws IllegalStateException, IOException;


}
