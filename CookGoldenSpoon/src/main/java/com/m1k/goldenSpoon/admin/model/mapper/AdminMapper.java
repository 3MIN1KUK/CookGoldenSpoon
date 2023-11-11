package com.m1k.goldenSpoon.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;

@Mapper
public interface AdminMapper {

	/** 회원 수 조회
	 * @return
	 */
	int selectListCount();

	/** 회원 조회
	 * @param searchMember
	 * @param rowBounds
	 * @return
	 */
	List<Member> selectMember(RowBounds rowBounds);

	/** 검색 회원 수 조회
	 * @param paramMap
	 * @return
	 */
	int searchListCount(Map<String, Object> paramMap);

	/** 회원 검색 조회
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Member> searchMember(Map<String, Object> paramMap, RowBounds rowBounds);

	/** 회원 권한 변경
	 * @param member
	 * @return
	 */
	int changeAuthority(Member member);

	/** 회원 상세 조회
	 * @param memberNo
	 * @return
	 */
	Member memberDetail(int memberNo);

	/** 레시피 개수 구하기
	 * @param searchMap
	 * @return
	 */
	int recipeListCount(Map<String, Object> searchMap);

	/** 레시피 조회
	 * @param searchMap
	 * @param rowBounds
	 * @return
	 */
	List<Recipe> recipeSelect(Map<String, Object> searchMap, RowBounds rowBounds);

	/** 게시글 개수 구하기
	 * @param map
	 * @return
	 */
	int boardListCount(Map<String, Object> map);

	/** 회원 번호들 구하기
	 * @param searchBoard
	 * @return
	 */
	List<Integer> getMemberNos(String memberNickname);

	/**  관리자 게시글 다중 검색
	 * @param map
	 * @param rowBounds
	 * @return
	 */
	List<Board> boardResult(Map<String, Object> map, RowBounds rowBounds);


}
