package com.m1k.goldenSpoon.myPage.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

@Mapper
public interface MyPageMapper {

	/** 마이페이지 들어온 멤버 조회
	 * @param memberNo
	 * @return
	 */
	Member myPage(int memberNo);

	/** 좋아요 누른 레시피 수 조회
	 * @param memberNo 
	 * @return
	 */
	int getRecipeListCount(int memberNo);

	/** 좋아요 누늘 레시피 조회
	 * @param memberNo
	 * @param recipeRowBounds
	 * @return
	 */
	List<Recipe> recipeSelect(int memberNo, RowBounds recipeRowBounds);

	/** 좋아요 누른 게시글 수 조회
	 * @param memberNo
	 * @return
	 */
	int getBoardListCount(int memberNo);

	/** 좋아요 누른 게시글 조회
	 * @param memberNo
	 * @param boardRowBounds
	 * @return
	 */
	List<Board> boardSelect(int memberNo, RowBounds boardRowBounds);

	/** 내가 쓴 레시피 수 조회
	 * @param memberNo
	 * @return
	 */
	int getMyRecipeListCount(int memberNo);

	/** 내가 쓴 레시피 조회
	 * @param memberNo
	 * @param rowBounds
	 * @return
	 */
	List<Recipe> myRecipeSelect(int memberNo, RowBounds rowBounds);

	/** 비동기 프로필 사진 변경
	 * @param loginMember
	 * @return
	 */
	int myPageEditProfile(Member loginMember);

}
