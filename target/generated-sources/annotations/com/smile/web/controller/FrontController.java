/*
 * 주제(Subject): Board Project (Paging) - Maven with Oracle 19, HikariCP 3.4.2	
 * 생성일자(Create Date): 2020-09-30
 * 저자(Author): Dodo (rabbit.white at daum dot net)
 * 파일명(FileName): FrontController.java
 * 설명(Description):
 * 
 * 
 */

package com.smile.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smile.web.db.DBFactory;

public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String charset = null;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doAction(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doAction(req, res);
	}
	
	// FrontController 패턴 & Command 패턴
	protected void doAction(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			ServletConfig sc = this.getServletConfig();
			charset = sc.getInitParameter("charset");

			req.setAttribute("charset", charset);
			req.setCharacterEncoding(charset);
			res.setContentType("text/html; charset=" + charset);

			String uri = req.getRequestURI();
			String conPath = req.getContextPath();
			String command = uri.substring(conPath.length());

			Controller subController = null;

			if(command.equals("/board/list.do")){
				System.out.println("-----------------------------");
				System.out.println("게시판 목록");
				System.out.println("-----------------------------");
				
				req.setAttribute("controllerName", "list");
				
				subController = new com.smile.web.controller.board.ListController();
				subController.execute(req, res);
			}
			
	}

}
