/*
 * 주제(Subject): Board Project (Paging) - Maven with Oracle 19, HikariCP 3.4.2	
 * 생성일자(Create Date): 2020-09-30
 * 저자(Author): Dodo (rabbit.white at daum dot net)
 * 파일명(FileName): ListController.java
 * 설명(Description):
 * 
 * 개발영역 관점에서의 페이징 처리
 * 
 */

package com.smile.web.controller.board;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smile.web.controller.Controller;
import com.smile.web.logic.Paging;
import com.smile.web.model.Board;
import com.smile.web.service.BoardService;
import com.smile.web.util.HttpUtil;

public class ListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String controllerName = (String) req.getAttribute("controllerName");
		BoardService service = BoardService.getInstance();
		
		List<Board> boardList = null;
		// long totalCount = boardList.size();
		
		long currentPage = 1;				// 기본값
		long pageSize = 10;
		long totalCount = service.getTotalCount();
		long startNum, endNum;
		
		String keyword = null;
		String pagingUrl = controllerName + ".do?";
		
		// 페이지 번호 존재할 때
		if (req.getParameter("page") != null){
			currentPage = Integer.valueOf( req.getParameter("page") );
		}
		
		// 키워드가 존재할 때
		if (req.getParameter("keyword") != null) {
			
			keyword = req.getParameter("keyword");
			totalCount = service.getTotalKeywordCount(keyword);
			
			// 키워드 값이 하나라도 존재할 때
			if ( keyword.length() > 0) {
				pagingUrl = pagingUrl + "keyword=" + URLEncoder.encode(keyword, "UTF-8") + "&";
			}
			
		}
		
		
        Paging paging = new Paging();
        /*
        paging.setPageNo(1);
        paging.setPageSize(10);
        paging.setTotalCount(totalCount);
        */
        
        paging.setPageNo(currentPage);
        paging.setPageSize(pageSize);
        paging.setTotalCount(totalCount);

        System.out.println("현재페이지번호:" + currentPage);
        System.out.println("페이지크기:" + pageSize);
        System.out.println("키워드:" + keyword);
        System.out.println("페이징URL:" + pagingUrl);
        
        startNum = paging.getDbStartNum();
        endNum = paging.getDbEndNum();

        
		// 키워드가 존재할 때
		if (keyword != null) {
			
			// 키워드 값이 하나라도 존재할 때
			if ( keyword.length() > 0) {
				boardList = service.getBoardKeywordList(keyword, startNum, endNum);
			}
			else {
				boardList = service.getBoardList(startNum, endNum);
			}
		}
		else {
	        boardList = service.getBoardList(startNum, endNum);
		}
        
        req.setAttribute("paging", paging);
        req.setAttribute("list", boardList);
        req.setAttribute("pagingUrl", pagingUrl);
		
		HttpUtil.forward(req, res, "/WEB-INF/views/board/" + controllerName + ".jsp");
	}

}
