package com.m1k.goldenSpoon.recipe.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeMaterial;
import com.m1k.goldenSpoon.recipe.model.dto.RecipePicture;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeStep;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeTag;

@Mapper
public interface EditRecipeMapper {

	/** 게시글 삭제
	 * @param map
	 * @return
	 */
	int deleteRecipe(Map<String, Object> map);

	/** 레시피 부분 수정
	 * @param recipe
	 * @return
	 */
	int updateRecipe(Recipe recipe);

	/** 레시피 별 재료 수정
	 * @param materialList
	 * @return
	 */
	int updateRecipeMaterial(List<RecipeMaterial> materialList);

	/** 레시피 태그 수정
	 * @param tagList
	 * @return
	 */
	int updateRecipeTag(List<RecipeTag> tagList);

	/** 요리 과정 수정
	 * @param uploadList1
	 * @return
	 */
	int updateProcessList(List<RecipeStep> uploadList1);

	/** 완성 사진 수정
	 * @param uploadList2
	 * @return
	 */
	int updateCompleteList(List<RecipePicture> uploadList2);

	/** 레시피 별 재료 삭제
	 * @param recipeNo
	 * @return
	 */
	int delMaterial(int recipeNo);

	/** 태그 삭제
	 * @param recipeNo
	 * @return
	 */
	int delTag(int recipeNo);

	/** 완성 사진 이미지 삭제
	 * @param map
	 * @return
	 */
	int completeImageDelete(Map<String, Object> map);

	/** 완성 사진 업데이트
	 * @param img
	 * @return
	 */
	int updateCompleteImg(RecipePicture img);

	/** 완성 사진 하나씩 삽입
	 * @param img
	 */
	void insertCompleteImg(RecipePicture img);

	/** 썸네일 삭제
	 * @param recipeNo
	 * @return
	 */
	int thumbnailDelete(int recipeNo);

	/** 과정 삭제
	 * @param recipeNo
	 * @return
	 */
	int delStep(int recipeNo);

	/** 과정 이미지 경로
	 * @param recipeNo
	 * @return
	 */
	List<String> stepImgPath(int recipeNo);

	/** 사진이 올라가있는 과정 업데이트
	 * @param step
	 */
	void updateRecipeStep(RecipeStep step);

	/** 레시피 과정 삭제
	 * @param step
	 */
	void deleteRecipeStep(RecipeStep step);

	/** 레시피 과정 삽입
	 * @param step
	 */
	int insertRecipeStep(RecipeStep step);

	/** 원래 총 스텝 개수 조회
	 * @param recipeNo
	 * @return
	 */
	int selectRecipeStepCount(int recipeNo);

	/** 넘치는 숫자 제거
	 * @param map
	 * @return 
	 */
	int deleteRecipeStep2(Map<String, Object> map);


}
