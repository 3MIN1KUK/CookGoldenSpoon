package com.m1k.goldenSpoon.recipe.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

@Mapper
public interface RecipeMapper {

	Recipe enroll(Recipe recipe);
	/** 레시피 상세 조회
	 * @param recipeNo
	 * @return recipe
	 */
	Recipe recipeDetail(int recipeNo);

	/** 레시피 글 수 전체 조회
	 * @return
	 */
	int listCount();

	/** 레시피 조회
	 * @param rowBounds
	 * @return
	 */
	List<Recipe> selectRecipe(RowBounds rowBounds);

	/** 레시피 검색 글 수 전체 조회
	 * @param inputSearch
	 * @return
	 */
	int listSearchCount(String inputSearch);

	/** 레시피 검색
	 * @param inputSearch
	 * @param rowBounds
	 * @return
	 */
	List<Recipe> selectSearchRecipe(String inputSearch, RowBounds rowBounds);
	
	/** 좋아요 삭제
	 * @param paramMap
	 * @return
	 */
	int deleteRecipeLike(Map<String, Object> paramMap);
	
	/** 좋아요 삽입
	 * @param paramMap
	 * @return
	 */
	int insertRecipeLike(Map<String, Object> paramMap);
	
	/** 좋아요 수 조회
	 * @param integer
	 * @return
	 */
	int countRecipeLike(Integer integer);
	
	/** 북마크 삭제
	 * @param paramMap
	 * @return
	 */
	int deleteRecipeBookmark(Map<String, Object> paramMap);
	
	/** 북마크 삽입
	 * @param paramMap
	 * @return
	 */
	int insertRecipeBookmark(Map<String, Object> paramMap);

}
