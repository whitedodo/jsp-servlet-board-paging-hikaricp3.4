/*
 * 주제(Subject): Board Project (Paging) - Maven with Oracle 19, HikariCP 3.4.2	
 * 생성일자(Create Date): 2020-09-30
 * 저자(Author): Dodo (rabbit.white at daum dot net)
 * 파일명(FileName): BoardService.java
 * 설명(Description):
 * 
 * 
 */

package com.smile.web.service;

import java.util.List;

import com.smile.web.model.Board;

public class BoardService {
	
	private static BoardService service = null;
	private static BoardServiceImpl dao = null;
	
	private BoardService() {}
	
	public static BoardService getInstance() {

        if(service == null){
        	service = new BoardService();
    		dao = BoardServiceImpl.getInstatnce();		
        }

        return service;
	}
	
	public List<Board> getBoardList(long startNum, long endNum){
		
		return dao.getBoardList(startNum, endNum);
	}
	
	public long getTotalCount() {
		
		return dao.getTotalCount();
	}
	
	public List<Board> getBoardKeywordList(String keyword, long startNum, long endNum){
		
		return dao.getBoardKeywordList(keyword, startNum, endNum);
	}

	public long getTotalKeywordCount(String keyword) {
		
		return dao.getTotalKeywordCount(keyword);
	}
	
}
