package com.m1k.goldenSpoon.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.m1k.goldenSpoon.board.model.dto.Board;
import com.m1k.goldenSpoon.board.model.dto.BoardImg;
import com.m1k.goldenSpoon.board.model.exception.BoardUpdateException;
import com.m1k.goldenSpoon.board.model.exception.BoardWriteException;
import com.m1k.goldenSpoon.board.model.mapper.EditBoardMapper;
import com.m1k.goldenSpoon.common.utility.Util;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
public class EditBoardServiceImpl implements EditBoardService{
	
	private final EditBoardMapper mapper;
	
	@Value("${my.board.location}")
	private String folderPath; // 서버 저장 폴더 경로
	
	@Value("${my.board.webpath}")
	private String webPath; // 웹 이미지 요청 경로
	
	
	// 게시글 삭제
	@Override
	public int deleteBoard(Map<String, Integer> paramMap) {
		return mapper.deleteBoard(paramMap);
	}
	
	// 게시글 작성
	@Override
	public int insertBoard(Board board, List<MultipartFile> images) throws IllegalStateException, IOException {
		
		
		// 게시글 board테이블만 insert
		int result = mapper.insertBoard(board);
		
		if(result == 0) return 0; // 삽입 실패
		
		int boardNo = board.getBoardNo();
		
		// ---------------------------------------
		
		// 게시글 작성 성공 시 이미지가 있으면 이미지 insert
		
		List<BoardImg> uploadList = new ArrayList<>();
		
		for(int i=0 ; i<images.size() ; i++) {
			
			if( images.get(i).getSize() > 0 ) {
				
				BoardImg img = new BoardImg();
				
				img.setBoardNo(boardNo);
				img.setBoardImageOrder(i);
				
				img.setBoardImageName( images.get(i).getOriginalFilename() );
				
				img.setBoardImage(webPath);
				
				img.setBoardImageRename(Util.fileRename( images.get(i).getOriginalFilename() ));
				
				img.setUploadFile(images.get(i));
				
				uploadList.add(img);
			}
		}
		
		if(uploadList.isEmpty()) {
			return boardNo;
		}
		
		result = mapper.insertUploadList(uploadList);
		
		if(result == uploadList.size()) {
			
			for(BoardImg img : uploadList) {
				img.getUploadFile().transferTo(new File(folderPath + img.getBoardImageRename()));
			}
		} else {
			throw new BoardWriteException("파일 정보 DB 삽입 실패");
		}
		return boardNo;
	}
	
	// 게시글 수정
	@Override
	public int updateBoard(Board board, List<MultipartFile> images, String deleteOrder) throws IllegalStateException, IOException {
		
		int result = mapper.updateBoard(board);
		
		if(result == 0) return 0;
		
		if( !deleteOrder.equals("") ) {
			Map<String, Object> map = new HashMap<>();
			map.put("boardNo", board.getBoardNo());
			map.put("deleteOrder", deleteOrder);
			
			result = mapper.imageDelete(map);
			
			if(result == 0) {
				throw new BoardUpdateException("이미 삭제 실패");
			}
		}
		
		List<BoardImg> uploadList = new ArrayList<>();
		
		for(int i=0 ; i<images.size() ; i++ ) {
			if( images.get(i).getSize() > 0 ) {
				BoardImg img = new BoardImg();
				img.setBoardNo(board.getBoardNo());
				img.setBoardImageOrder(i);
				img.setBoardImageName( images.get(i).getOriginalFilename());
				img.setBoardImage(webPath);
				img.setBoardImageRename(Util.fileRename( images.get(i).getOriginalFilename() ));
				img.setUploadFile(images.get(i));
				uploadList.add(img);
				
				result = mapper.updateBoardImg(img);
				
				if(result == 0) {
					mapper.boardImgInsert(img);
				}
			}
		}
		
		if( !uploadList.isEmpty() ) {
			result = 1;
			for(BoardImg img : uploadList) {
				img.getUploadFile().transferTo( new File( folderPath + img.getBoardImageRename() ) );
			}
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
