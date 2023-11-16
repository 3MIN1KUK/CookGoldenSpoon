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
import com.m1k.goldenSpoon.board.model.exception.BoardWriteException;
import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.common.utility.Util;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeMaterial;
import com.m1k.goldenSpoon.recipe.model.dto.RecipePicture;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeStep;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeTag;
import com.m1k.goldenSpoon.recipe.model.mapper.RecipeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{

	private final RecipeMapper mapper;
	
	@Value("${my.recipe.location}")
	private String folderPath;
	
	@Value("${my.recipe.webpath}")
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
	public Map<String, Object> search(int cp, String inputSearch, int orderBy) {
		int listCount = 0;
		if(orderBy == 5) {
			listCount = mapper.listWriterCount(inputSearch);
		} else {
			listCount = mapper.listSearchCount(inputSearch);
		}
		Pagination pagination = new Pagination(cp, listCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inputSearch", inputSearch);
		paramMap.put("orderBy", orderBy);
		List<Recipe> recipeList = null;
		if(orderBy == 5) {
			recipeList = mapper.selectWriterRecipe(inputSearch, rowBounds);
		} else {
			recipeList = mapper.selectSearchRecipe(paramMap, rowBounds);
		}
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
		Pagination pagination = new Pagination(cp, 16, 16, 1);
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
	public int enroll(Recipe recipe, String originRecipeVideo, MultipartFile thumbnail,
			 List<String> recipeTagName, List<String> recipeStepContent, 
			 List<MultipartFile> recipeStepImage, List<MultipartFile> completeImages
			 , List<String> materialName, List<String> recipeMaterialQuantity) 
					 throws IllegalStateException, IOException {
		if(originRecipeVideo.length() != 0) {
			String recipeVideo = originRecipeVideo.replace("watch?v=","embed/");
			if(recipeVideo.length() >= recipeVideo.indexOf("embed/")+17) {
				recipe.setRecipeVideo(recipeVideo.substring(0, recipeVideo.indexOf("embed/")+17));
			}
		}
		
		if(thumbnail.getSize() > 0) {
			String thumbnailRename = Util.fileRename(thumbnail.getOriginalFilename());
			recipe.setRecipeThumbnail(webPath + thumbnailRename);
			thumbnail.transferTo(new File(folderPath + thumbnailRename));
		}
		
		int result1 = mapper.insertRecipe(recipe);
		if(result1 == 0) return 0; 
		int recipeNo = recipe.getRecipeNo();
		
		List<RecipeMaterial> materialList = new ArrayList<>();
		
		for(int i = 0; i<materialName.size(); i++) {
			if(materialName.get(i).trim().length() != 0) {
				int materialResult = mapper.checkMaterial(materialName.get(i));
				
				if(materialResult == 0) {
					mapper.insertMaterialName(materialName.get(i));
				}
					
					RecipeMaterial material = new RecipeMaterial();
					material.setMaterialName(materialName.get(i));
					material.setRecipeMaterialOrder(i);
					material.setRecipeMaterialQuantity(recipeMaterialQuantity.get(i));
					material.setRecipeNo(recipeNo);
					materialList.add(material);
					
			}
		}
		int result2 = mapper.insertRecipeMaterial(materialList);
		if(result2 == 0) return 0;
		
		List<RecipeTag> tagList = new ArrayList<>();
		for(int i = 0 ; i<recipeTagName.size(); i++) {
			RecipeTag tag = new RecipeTag();
			tag.setRecipeNo(recipeNo);
			tag.setRecipeTagName(recipeTagName.get(i));
			tagList.add(tag);
		}
		int result3 = mapper.insertRecipeTag(tagList);
		if(result3 == 0) return 0;
		
		
		List<RecipeStep> uploadList1 = new ArrayList<>();
		for(int i = 0 ; i<recipeStepContent.size(); i++) {
			RecipeStep step = new RecipeStep();
			step.setRecipeNo(recipeNo); 
			step.setRecipeStepContent(recipeStepContent.get(i));
			step.setRecipeStepOrder(i);
			if(recipeStepImage.get(i).getSize() > 0) {
				step.setRecipeStepImageName( recipeStepImage.get(i).getOriginalFilename() ); 
				step.setRecipeStepImage(webPath);
				step.setRecipeStepImageRename(Util.fileRename( recipeStepImage.get(i).getOriginalFilename() ));
				step.setUploadFile(recipeStepImage.get(i));
				
			} // if문 끝
			uploadList1.add(step);
		}// for문 끝
		int result4 = mapper.insertProcessList(uploadList1);
		
		List<RecipePicture> uploadList2 = new ArrayList<>();
		for(int i = 0 ; i<completeImages.size(); i++) {
			if(completeImages.get(i).getSize() > 0) {
				RecipePicture img = new RecipePicture();
				img.setRecipeNo(recipeNo); 
				img.setRecipeImageOrder(i);
				img.setRecipeImageName( completeImages.get(i).getOriginalFilename() ); 
				img.setRecipeImage(webPath);
				img.setRecipeImageRename(Util.fileRename( completeImages.get(i).getOriginalFilename() ));
				img.setUploadFile(completeImages.get(i));
				uploadList2.add(img);
			} // if문 끝
		}// for문 끝
		if(uploadList2.size() > 0) {
			int result5 = mapper.insertCompleteList(uploadList2);
			
			if(result1 > 0 && result4 == uploadList1.size() && result5 == uploadList2.size()) {
				
				
				result4 = uploadList1.size();
				for(RecipeStep img : uploadList1) {
					if(img.getUploadFile() != null) {
						img.getUploadFile().transferTo(new File(folderPath + img.getRecipeStepImageRename()));
					}
				}
				
				result5 = uploadList2.size();
				for(RecipePicture img : uploadList2) {
					img.getUploadFile().transferTo(new File(folderPath + img.getRecipeImageRename()));
				}
			}
			
		}
		
		
		
		
		return recipeNo;
	}
	
	
	
	// 레시피 조회 수 증가
	@Override
	public int updateRecipeHits(int recipeNo) {
		return mapper.updateRecipeHits(recipeNo);
	}
	
	
	
}
