package com.m1k.goldenSpoon.recipe.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.m1k.goldenSpoon.recipe.model.exception.RecipeUpdateException;
import com.m1k.goldenSpoon.recipe.model.mapper.EditRecipeMapper;
import com.m1k.goldenSpoon.recipe.model.mapper.RecipeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EditRecipeServiceImpl implements EditRecipeService{

	private final EditRecipeMapper mapper;
	private final RecipeMapper recipeMapper;

	@Value("${my.recipe.location}")
	private String folderPath;

	@Value("${my.recipe.webpath}")
	private String webPath;

	// 레시피 삭제
	@Override
	public int deleteRecipe(Map<String, Object> map) {
		return mapper.deleteRecipe(map);
	}

	// 레시피 수정
	@Override
	public int update(Recipe recipe, String originRecipeVideo, MultipartFile thumbnail, List<String> recipeTagName,
			List<String> recipeStepContent, List<MultipartFile> recipeStepImage, List<MultipartFile> completeImages,
			List<String> materialName, List<String> recipeMaterialQuantity,
			String deleteCompleteOrder, String deleteThumbnail) throws IllegalStateException, IOException {
		String recipeVideo = originRecipeVideo.replace("watch?v=","embed/");
		recipe.setRecipeVideo(recipeVideo);
		
		
		int result = mapper.updateRecipe(recipe);
		if(result == 0) return 0; 
		
		// 썸네일이 삭제됐을 때
		if(!deleteThumbnail.equals("")) {
			mapper.thumbnailDelete(recipe.getRecipeNo());
		}
		if(thumbnail.getSize() > 0) {
			String thumbnailRename = Util.fileRename(thumbnail.getOriginalFilename());
			recipe.setRecipeThumbnail(webPath + thumbnailRename);
			thumbnail.transferTo(new File(folderPath + thumbnailRename));
		}
		
		int delMaterial = mapper.delMaterial(recipe.getRecipeNo());
		if(delMaterial == 0) return 0;
		
		List<RecipeMaterial> materialList = new ArrayList<>();

		for(int i = 0; i<materialName.size(); i++) {
			if(materialName.get(i).trim().length() != 0) {
				int materialResult = recipeMapper.checkMaterial(materialName.get(i));

				if(materialResult == 0) {
					recipeMapper.insertMaterialName(materialName.get(i));
				}

				RecipeMaterial material = new RecipeMaterial();
				material.setMaterialName(materialName.get(i));
				material.setRecipeMaterialOrder(i);
				material.setRecipeMaterialQuantity(recipeMaterialQuantity.get(i));
				material.setRecipeNo(recipe.getRecipeNo());
				materialList.add(material);

			}
		}
		int result2 = recipeMapper.insertRecipeMaterial(materialList);
		if(result2 == 0) return 0;
		
		int delTag = mapper.delTag(recipe.getRecipeNo());
		if(delTag == 0) return 0;
		
		List<RecipeTag> tagList = new ArrayList<>();
		for(int i = 0 ; i<recipeTagName.size(); i++) {
			RecipeTag tag = new RecipeTag();
			tag.setRecipeNo(recipe.getRecipeNo());
			tag.setRecipeTagName(recipeTagName.get(i));
			tagList.add(tag);
		}
		int result3 = recipeMapper.insertRecipeTag(tagList);
		if(result3 == 0) return 0;
		
		
		if(!deleteCompleteOrder.equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("recipeNo", recipe.getRecipeNo());
			map.put("deleteCompleteOrder", deleteCompleteOrder);
			
			int delCompleteresult = mapper.completeImageDelete(map);
			
			if(delCompleteresult == 0) { // 실패 시 전체 롤백 => 예외 강제 발생 시키기
				throw new RecipeUpdateException("이미지 삭제 실패");
			}
		}
		List<RecipePicture> uploadList2 = new ArrayList<>();
		System.out.println("=================completeImages.size()=================== " + completeImages.size());
		
		for(int i = 0 ; i<completeImages.size(); i++) {
			if(completeImages.get(i).getSize() > 0) {
				RecipePicture img = new RecipePicture();
				img.setRecipeNo(recipe.getRecipeNo()); 
				img.setRecipeImageOrder(i);
				img.setRecipeImageName( completeImages.get(i).getOriginalFilename() ); 
				img.setRecipeImage(webPath);
				img.setRecipeImageRename(Util.fileRename( completeImages.get(i).getOriginalFilename() ));
				img.setUploadFile(completeImages.get(i));
				uploadList2.add(img);
				
				int completeImgResult = mapper.updateCompleteImg(img);
				
				if(completeImgResult == 0) {
					mapper.insertCompleteImg(img);
				}
			} // if문 끝
		}// for문 끝

		// 과정 이미지 경로 불러오기
		List<String> stepImgPath = mapper.stepImgPath(recipe.getRecipeNo());
		for(int i = 0; i<stepImgPath.size(); i++) {
			File deleteFile = new File(folderPath + stepImgPath.get(i));
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
		}

		int delStep = mapper.delStep(recipe.getRecipeNo());
		if(delStep == 0) return 0;

		List<RecipeStep> uploadList1 = new ArrayList<>();
		for(int i = 0 ; i<recipeStepContent.size(); i++) {
			RecipeStep step = new RecipeStep();
			step.setRecipeNo(recipe.getRecipeNo()); 
			step.setRecipeStepContent(recipeStepContent.get(i));
			step.setRecipeStepOrder(i);
			if(recipeStepImage.get(i).getSize() > 0 && recipeStepImage.get(i) != null) {
				step.setRecipeStepImageName( recipeStepImage.get(i).getOriginalFilename() ); 
				step.setRecipeStepImage(webPath);
				step.setRecipeStepImageRename(Util.fileRename( recipeStepImage.get(i).getOriginalFilename() ));
				step.setUploadFile(recipeStepImage.get(i));

			} // if문 끝
			uploadList1.add(step);
		}// for문 끝

		int result4 = recipeMapper.insertProcessList(uploadList1);
		System.out.println("======================================" + uploadList1.size());
		System.out.println("======================================" + result4);
		System.out.println("====================================" + result);
		System.out.println("====================================" + uploadList2.isEmpty());
		System.out.println("====================================" + thumbnail.getSize());
		

		if(!uploadList2.isEmpty() && result4 == uploadList1.size() && result > 0) {
			result = 1;
			System.out.println("=====================================================================");
			System.out.println("=====================================================================");
			System.out.println("=====================================================================");
			
			for(RecipePicture img : uploadList2) {
				img.getUploadFile().transferTo(new File(folderPath + img.getRecipeImageRename()));
			}
			for(RecipeStep img : uploadList1) {
				img.getUploadFile().transferTo(new File(folderPath + img.getRecipeStepImageRename()));
			}
		}
		
		return result;
	}
}
