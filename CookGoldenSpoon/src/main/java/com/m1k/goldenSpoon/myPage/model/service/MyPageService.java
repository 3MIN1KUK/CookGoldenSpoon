package com.m1k.goldenSpoon.myPage.model.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.member.model.dto.Member;

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
	

}
