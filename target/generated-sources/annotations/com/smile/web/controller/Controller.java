/*
 * 주제(Subject): Board Project (Paging) - Maven with Oracle 19, HikariCP 3.4.2	
 * 생성일자(Create Date): 2020-09-30
 * 저자(Author): Dodo (rabbit.white at daum dot net)
 * 파일명(FileName): Controller.java
 * 설명(Description):
 * 
 * 
 */

package com.smile.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}