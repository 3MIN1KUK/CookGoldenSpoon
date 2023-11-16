package com.m1k.goldenSpoon.myPage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.myPage.model.dto.MyPagePwChange;
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

	/** 닉네임 유효성 검사
	 * @param memberNickname
	 * @return
	 */
	int myPageValidation(String memberNickname);

	/** 내 정보 수정
	 * @param loginMember
	 * @return
	 */
	int myPageEdit(Member loginMember);

	/** 로그인 멤버 현재 비밀번호 가져오기
	 * @param pwChange
	 * @return
	 */
	String getMemberPw(MyPagePwChange pwChange);

	/** 비밀번호 변경
	 * @param pwChange
	 * @return
	 */
	int changePw(MyPagePwChange pwChange);

	/** 북마크한 글 개수
	 * @param map
	 * @return
	 */
	int getMyPageBookmarkSearchListCount(Map<String, Object> map);

	/** 북마크한 글 검색
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Recipe> myPageBookmarkSearchSelect(Map<String, Object> paramMap, RowBounds rowBounds);

	/** 북마크한 글 작성자로 검색 개수
	 * @param paramMap
	 * @return
	 */
	int getMyPageBookmarkWriterListCount(Map<String, Object> paramMap);

	/** 북마크한 글 작성자로 검색
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Recipe> myPageBookmarkWriterSelect(Map<String, Object> paramMap, RowBounds rowBounds);

	/** 내가 쓴 레시피에서 검색 개수
	 * @param paramMap
	 * @return
	 */
	int getMyPageRecipeSearchListCount(Map<String, Object> paramMap);

	/** 내가 쓴 레시피에서 검색
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Recipe> myPageRecipeSearchSelect(Map<String, Object> paramMap, RowBounds rowBounds);

	/** 내가 쓴 글 검색 개수
	 * @param paramMap
	 * @return
	 */
	int getMyPageBoardSearchListCount(Map<String, Object> paramMap);

	/** 내가 쓴 글 검색
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Board> getMyPageBoardSearchSelect(Map<String, Object> paramMap, RowBounds rowBounds);

}
