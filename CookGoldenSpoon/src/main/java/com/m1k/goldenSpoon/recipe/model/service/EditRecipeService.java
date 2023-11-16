package com.m1k.goldenSpoon.recipe.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

public interface EditRecipeService {

	/** 레시피 삭제
	 * @param map
	 * @return
	 */
	int deleteRecipe(Map<String, Object> map);

	/** 레시피 수정
	 * @param recipe
	 * @param originRecipeVideo
	 * @param thumbnail
	 * @param recipeTagName
	 * @param recipeStepContent
	 * @param recipeStepImage
	 * @param completeImages
	 * @param materialName
	 * @param recipeMaterialQuantity
	 * @param deleteCompleteOrder 
	 * @param deleteThumbnail 
	 * @param stepImg 
	 * @return
	 */
	int update(Recipe recipe, String originRecipeVideo, MultipartFile thumbnail, List<String> recipeTagName,
			List<String> recipeStepContent, List<MultipartFile> recipeStepImage, List<MultipartFile> completeImages,
			List<String> materialName, List<String> recipeMaterialQuantity, String deleteCompleteOrder, String deleteThumbnail, List<String> stepImg) throws IllegalStateException, IOException;

}
