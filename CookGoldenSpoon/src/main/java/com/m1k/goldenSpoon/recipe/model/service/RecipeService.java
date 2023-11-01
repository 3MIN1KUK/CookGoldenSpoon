package com.m1k.goldenSpoon.recipe.model.service;

import java.util.Map;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

public interface RecipeService {

	Recipe enroll(Recipe recipe);

	
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

}
