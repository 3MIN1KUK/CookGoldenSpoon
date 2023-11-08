package com.m1k.goldenSpoon.recipe.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.BoardImg;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

@Mapper
public interface RecipeMapper {


	
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
	
	/** 즐겨찾기 확인
	 * @param map
	 * @return
	 */
	int bookmarkCheck(Map<String, Integer> map);
	
	/** 좋아요 확인
	 * @param map
	 * @return
	 */
	int likeCheck(Map<String, Integer> map);
	
	/** 메인 페이지 인기 레시피
	 * @param rowBounds
	 * @return
	 */
	List<Recipe> selectPopularRecipe(RowBounds rowBounds);
	
	
	/** 별점 삽입
	 * @param paramMap
	 * @return
	 */
	int insertRecipeStar(Map<String, Object> paramMap);
	
	/** 별점 수정
	 * @param paramMap
	 * @return
	 */
	int updateRecipeStar(Map<String, Object> paramMap);
	/** 별점 수 체크
	 * @param map
	 * @return
	 */
	Object starsCheck(Map<String, Integer> map);
	/** 별점 null 체크
	 * @param map
	 * @return
	 */
//	int countStarsCheck(Map<String, Integer> map);


	/** 업로드된 이미지 정보 일괄 삽입
	 * @param uploadList
	 * @return
	 */
	int insertUploadList(List<BoardImg> uploadList);


	/** 레시피 등록
	 * @param recipe
	 * @return
	 */
	int insertRecipe(Recipe recipe);

	
	
}
