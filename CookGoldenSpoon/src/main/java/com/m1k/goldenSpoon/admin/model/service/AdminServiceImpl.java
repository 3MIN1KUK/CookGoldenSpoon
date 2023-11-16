package com.m1k.goldenSpoon.admin.model.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.m1k.goldenSpoon.admin.model.dto.unionComment;
import com.m1k.goldenSpoon.admin.model.mapper.AdminMapper;
import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.Report;
import com.m1k.goldenSpoon.common.model.dto.Pagination;
import com.m1k.goldenSpoon.member.model.dto.Instructor;
import com.m1k.goldenSpoon.member.model.dto.Member;
import com.m1k.goldenSpoon.recipe.model.dto.Recipe;
import com.m1k.goldenSpoon.recipe.model.dto.RecipeTag;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final AdminMapper mapper;
	
	// 회원 검색
	@Override
	public Map<String, Object> selectMember( int cp) {
		
		
		int listCount = mapper.selectListCount();
		
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> listMember = mapper.selectMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("listMember", listMember);
		map.put("pagination", pagination);
		
		return map;
	}
	
	// 회원 검색
	@Override
	public Map<String, Object> searchMember(Map<String, Object> paramMap, int cp) {
		
		
		int listCount = mapper.searchListCount(paramMap);
		
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> listMember = mapper.searchMember(paramMap, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("listMember", listMember);
		map.put("pagination", pagination);
		
		return map;
	}
	
	// 권한 변경
	@Override
	public int changeAuthority(Member member) {
		return mapper.changeAuthority(member);
	}
	
	// 회원 상세 조회
	@Override
	public Member memberDetail(int memberNo) {
		return mapper.memberDetail(memberNo);
	}
	
	
	// 레시피 검색
	@Override
	public Map<String, Object> recipeResult(Recipe searchRecipe, int cp) {
		
		if (searchRecipe.getRecipeTag() != null) {
			
			List<String> tagList = searchRecipe.getRecipeTag();
			
			for (int i = 1 ; i <= tagList.size() + 1 ; i++) {
				tagList.remove("");
			}
			searchRecipe.setRecipeTag(tagList);
		}

		
		// 검색한 이름이 포함된 회원번호 리스트 구하기
		List<Integer> memberNos= mapper.getMemberNos(searchRecipe.getMemberNickname());
		if (searchRecipe.getMemberNo() != 0) {
			memberNos.clear();
			memberNos.add(searchRecipe.getMemberNo());
		}
		Map<String, Object> map = new HashMap<>();
		
		map.put("memberNos", memberNos);
		map.put("searchRecipe", searchRecipe);
		
		
		int myRecipeListCount = mapper.recipeListCount(map);
		Pagination pagination = new Pagination(cp, myRecipeListCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds RowBounds = new RowBounds(offset, limit);
		List<Recipe> recipeList = mapper.recipeSelect(map, RowBounds);
		
		map.put("recipeList", recipeList);
		map.put("pagination", pagination);

		return map;
	}
	
	// 게시글 검색
	@Override
	public Map<String, Object> boardResult(Board searchBoard, int cp) {
		
		
		// 검색한 이름이 포함된 회원번호 리스트 구하기
		List<Integer> memberNos= mapper.getMemberNos(searchBoard.getMemberNickname());
		if (searchBoard.getMemberNo() != 0) {
			memberNos.clear();
			memberNos.add(searchBoard.getMemberNo());
		}
		Map<String, Object> map = new HashMap<>();
		
		map.put("memberNos", memberNos);
		map.put("searchBoard", searchBoard);
		
		// 게시판 개수 구하기
		int boardListCount = mapper.boardListCount(map); 
		
		Pagination pagination = new Pagination(cp, boardListCount, 14, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds RowBounds = new RowBounds(offset, limit);
		
		// 게시판 구하기
		List<Board> boardList = mapper.boardResult(map, RowBounds);
		
		map.put("boardList", boardList);
		map.put("pagination", pagination);
		
		
		return map;
	}
	
	// 댓글 검색
	@Override
	public Map<String, Object> commentResult(unionComment searchComment, int cp) {
		
		// 검색한 이름이 포함된 회원번호 리스트 구하기
		List<Integer> memberNos = mapper.getMemberNos(searchComment.getMemberNickname() );
		if (searchComment.getMemberNo() != 0) {
			memberNos.clear();
			memberNos.add(searchComment.getMemberNo());
		}
		Map<String, Object> map = new HashMap<>();
		
		map.put("memberNos", memberNos);
		map.put("searchComment", searchComment);
		
		// 댓글 개수 구하기
		int commentListCount = mapper.commentListCount(map);
		
		Pagination pagination = new Pagination(cp, commentListCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds RowBounds = new RowBounds(offset, limit);
		
		List<unionComment> commentList = mapper.commentResult(map, RowBounds);
		
		map.put("commentList", commentList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	
	// 미승인 강사승인 조회
	@Override
	public Map<String, Object> instructorApproval(Instructor searchInstructor, int cp) {
		
		
		// 미승인 강사신청 개수구하기
		int instructorListCount = mapper.instructorListCount();
		
		Pagination pagination = new Pagination(cp, instructorListCount, 8, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds RowBounds = new RowBounds(offset, limit);
		
		List<Instructor> instructorList = mapper.instructorApproval(RowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("instructorList", instructorList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	// 게시글 삭제
	@Override
	public int boardDelete(int boardNo) {
		return mapper.boardDelete(boardNo);
	}
	
	// 댓글 삭제
	@Override
	public int commentDelete(unionComment deleteComment) {
		
		int result = 0;
		
		if (deleteComment.getCommentType().equals("게시글")) {
			result = mapper.deleteBoardComment(deleteComment);
		}
		
		if (deleteComment.getCommentType().equals("레시피")) {
			result = mapper.deleteRecipeComment(deleteComment);
		}
		
		return result;
	}
	
	// 신고 전체 조회
	@Override
	public Map<String, Object> selectReport(int cp) {
		
		int reportListCount = mapper.selectLeportListCount();
		
		Pagination pagination = new Pagination(cp, reportListCount, 11, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds RowBounds = new RowBounds(offset, limit);
		
		Map<String, Object> map = new HashMap<>();
		List<Report> reportList = mapper.selectReport(RowBounds);
		
		map.put("pagination", pagination);
		map.put("reportList", reportList);
		
		return map;
	}
	
	@Override
	public Map<String, Object> searchReport(Report report, int cp) {
		
		int reportListCount = mapper.searchLeportListCount();
		
		Pagination pagination = new Pagination(cp, reportListCount, 11, 7);
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds RowBounds = new RowBounds(offset, limit);
		
		Map<String, Object> map = new HashMap<>();
		List<Report> reportList = mapper.searchReport(RowBounds);
		
		map.put("pagination", pagination);
		map.put("reportList", reportList);
		
		return map;
	}
	
	
	// 신고 상세 조회
	@Override
	public Report reportDetail(int reportNo, int cp) {
		
		return mapper.reportDetail(reportNo);
	}
	
	// 신고 답변 작성
	@Override
	public int reportAnswer(Report report) {
		return mapper.reportAnswer(report);
	}
	
	@Override
	public int changeMemberDelFl(Member member) {
		return mapper.changeMemberDelFl(member);
	}
	
	
	@Override
	public int changeBoardDelFl(Board board) {
		return mapper.changeBoardDelFl(board);
	}
	
	@Override
	public int changeCommentDelFl(unionComment comment) {
		
		System.out.println("========= comment.getCommentType() = " +comment.getCommentType());
		
		
		if (comment.getCommentType().equals("게시글")) {
			return mapper.changeBoardCommentDelFl(comment);
		}
		
		if (comment.getCommentType().equals("레시피")) {
			return mapper.changeRecipeCommentDelFl(comment);
		}
		
		return 0;
	}
	
	
	
}
