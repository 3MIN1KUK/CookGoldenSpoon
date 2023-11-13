package com.m1k.goldenSpoon.admin.model.service;

import java.util.List;
import java.util.Map;

import com.m1k.goldenSpoon.admin.model.dto.unionComment;
import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.member.model.dto.Instructor;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

public interface AdminService {

	/** 회원 조회
	 * @param searchMember
	 * @param cp 
	 * @return
	 */
	Map<String, Object> selectMember(int cp);

	/** 회원 검색 조회
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> searchMember(Map<String, Object> paramMap, int cp);

	/** 권한 변경
	 * @param member
	 * @return
	 */
	int changeAuthority(Member member);

	/** 회원 상세 조회
	 * @param memberNo
	 * @return
	 */
	Member memberDetail(int memberNo);

	/** 레시피 검색
	 * @param searchRecipe
	 * @param cp
	 * @return
	 */
	Map<String, Object> recipeResult(Recipe searchRecipe, int cp);

	/** 게시글 검색
	 * @param searchBoard
	 * @param cp
	 * @return
	 */
	Map<String, Object> boardResult(Board searchBoard, int cp);

	/** 댓글 통합 검색
	 * @param searchComment
	 * @param cp
	 * @return
	 */
	Map<String, Object> commentResult(unionComment searchComment, int cp);

	Map<String, Object> instructorApproval(Instructor searchInstructor, int cp);

	/** 게시글 삭제
	 * @param boardNo
	 * @return
	 */
	int boardDelete(int boardNo);

}
