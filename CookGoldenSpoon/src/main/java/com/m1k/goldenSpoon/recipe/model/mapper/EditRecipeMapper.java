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

}
