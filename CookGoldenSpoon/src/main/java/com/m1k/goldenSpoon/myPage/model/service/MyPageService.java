package com.m1k.goldenSpoon.myPage.model.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.myPage.model.dto.MyPagePwChange;

public interface MyPageService {

	/** 마이페이지에 들어온 멤버 조회
	 * @param memberNo
	 * @return
	 */
	Member myPage(int memberNo);

	/** 마이페이지 좋아요 누른 글 조회
	 * @param memberNo
	 * @param cp
	 * @return
	 */
	Map<String, Object> myPageLike(int memberNo, int cp);

	/** 마이페이지 북마크 누른 글 조회
	 * @param memberNo
	 * @param cp
	 * @return
	 */
	Map<String, Object> myPageBookmark(int memberNo, int cp);

	/** 내가 쓴 글 조회
	 * @param memberNo
	 * @param cp
	 * @return
	 */
	Map<String, Object> myPageBoard(int memberNo, int cp);

	/** 내가 쓴 레시피 조회
	 * @param memberNo
	 * @param cp
	 * @return
	 */
	Map<String, Object> myPageRecipe(int memberNo, int cp);

	/** 비동기 프로필 사진 변경
	 * @param memberProfile
	 * @param loginMember
	 * @return
	 */
	int myPageEditProfile(MultipartFile memberProfile, Member loginMember) throws IllegalStateException, IOException;

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

	/** 팝업 비밀번호 변경
	 * @param pwChange
	 * @return
	 */
	int myPageEditPw(MyPagePwChange pwChange);

	/** 북마크 검색
	 * @param memberNo
	 * @param cp
	 * @param orderBy
	 * @param inputSearch
	 * @return
	 */
	Map<String, Object> myPageBookmarkSearch(int memberNo, int cp, int orderBy, String inputSearch);
 
	/** 나의 레시피 검색
	 * @param memberNo
	 * @param cp
	 * @param inputSearch
	 * @param orderBy
	 * @return
	 */
	Map<String, Object> myPageRecipeSearch(int memberNo, int cp, String inputSearch, int orderBy);
 
	/** 나의 게시글 검색
	 * @param memberNo
	 * @param cp
	 * @param inputSearch
	 * @return
	 */
	Map<String, Object> myPageBoardSearch(int memberNo, int cp, String inputSearch);

	/** 탈퇴
	 * @param memberNo
	 * @return
	 */
	int myPageSecession(int memberNo);


}
