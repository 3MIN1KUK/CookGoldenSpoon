package com.m1k.goldenSpoon.recipe.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.common.utility.Util;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeMaterial;
import com.m1k.goldenSpoon.recipe.model.dto.RecipePicture;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeStep;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeTag;
import com.m1k.goldenSpoon.recipe.model.mapper.EditRecipeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EditRecipeServiceImpl implements EditRecipeService{

	private final EditRecipeMapper mapper;

	@Value("${my.recipe.location}")
	private String folderPath;

	@Value("${my.recipe.webpath}")
	private String webPath;

	// 레시피 삭제
	@Override
	public int deleteRecipe(Map<String, Object> map) {
		return mapper.deleteRecipe(map);
	}

//	// 레시피 수정
//	@Override
//	public int update(Recipe recipe, MultipartFile thumbnail, List<String> recipeTagName,
//			List<String> recipeStepContent, List<MultipartFile> recipeStepImage, List<MultipartFile> completeImages,
//			List<String> materialName, List<String> recipeMaterialQuantity) throws IllegalStateException, IOException {
//
//
//		String thumbnailRename = Util.fileRename(thumbnail.getOriginalFilename());
//
//		recipe.setRecipeThumbnail(webPath + thumbnailRename);
//
//		int result1 = mapper.updateRecipe(recipe);
//		if(result1 == 0) return 0; 
//		int recipeNo = recipe.getRecipeNo();
//
//		List<RecipeMaterial> materialList = new ArrayList<>();
//
//		for(int i = 0; i<materialName.size(); i++) {
//			if(materialName.get(i).trim().length() != 0) {
//				//					mapper.insertMaterialName(materialName.get(i));
//				RecipeMaterial material = new RecipeMaterial();
//				material.setMaterialName(materialName.get(i));
//				material.setRecipeMaterialOrder(i);
//				material.setRecipeMaterialQuantity(recipeMaterialQuantity.get(i));
//				material.setRecipeNo(recipeNo);
//				materialList.add(material);
//			}
//		}
//		int result2 = mapper.updateRecipeMaterial(materialList);
//		if(result2 == 0) return 0;
//
//		List<RecipeTag> tagList = new ArrayList<>();
//		for(int i = 0 ; i<recipeTagName.size(); i++) {
//			RecipeTag tag = new RecipeTag();
//			tag.setRecipeNo(recipeNo);
//			tag.setRecipeTagName(recipeTagName.get(i));
//			tagList.add(tag);
//		}
//		int result3 = mapper.updateRecipeTag(tagList);
//		if(result3 == 0) return 0;
//
//
//		List<RecipeStep> uploadList1 = new ArrayList<>();
//		for(int i = 0 ; i<recipeStepContent.size(); i++) {
//			RecipeStep step = new RecipeStep();
//			step.setRecipeNo(recipeNo); 
//			step.setRecipeStepContent(recipeStepContent.get(i));
//			step.setRecipeStepOrder(i);
//			if(recipeStepImage.get(i).getSize() > 0) {
//				step.setRecipeStepImageName( recipeStepImage.get(i).getOriginalFilename() ); 
//				step.setRecipeStepImage(webPath);
//				step.setRecipeStepImageRename(Util.fileRename( recipeStepImage.get(i).getOriginalFilename() ));
//				step.setUploadFile(recipeStepImage.get(i));
//
//			} // if문 끝
//			uploadList1.add(step);
//		}// for문 끝
//		int result4 = mapper.updateProcessList(uploadList1);
//
//		List<RecipePicture> uploadList2 = new ArrayList<>();
//		for(int i = 0 ; i<completeImages.size(); i++) {
//			if(completeImages.get(i).getSize() > 0) {
//				RecipePicture img = new RecipePicture();
//				img.setRecipeNo(recipeNo); 
//				img.setRecipeImageOrder(i);
//				img.setRecipeImageName( completeImages.get(i).getOriginalFilename() ); 
//				img.setRecipeImage(webPath);
//				img.setRecipeImageRename(Util.fileRename( completeImages.get(i).getOriginalFilename() ));
//				img.setUploadFile(completeImages.get(i));
//				uploadList2.add(img);
//			} // if문 끝
//		}// for문 끝
//
//		int result5 = mapper.updateCompleteList(uploadList2);
//
//
//
//		if(result1 > 0 && result4 == uploadList1.size() && result5 == uploadList2.size()) {
//			thumbnail.transferTo(new File(folderPath + thumbnailRename));
//
//			result4 = uploadList1.size();
//			for(RecipeStep img : uploadList1) {
//				if(img.getUploadFile() != null) {
//					img.getUploadFile().transferTo(new File(folderPath + img.getRecipeStepImageRename()));
//				}
//			}
//
//			result5 = uploadList2.size();
//			for(RecipePicture img : uploadList2) {
//				img.getUploadFile().transferTo(new File(folderPath + img.getRecipeImageRename()));
//			}
//		}
//
//		return recipeNo;
//	}

}
