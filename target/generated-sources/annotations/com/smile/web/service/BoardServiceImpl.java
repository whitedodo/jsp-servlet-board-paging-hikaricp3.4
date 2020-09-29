/*
 * 주제(Subject): Board Project (Paging) - Maven with Oracle 19, HikariCP 3.4.2	
 * 생성일자(Create Date): 2020-09-30
 * 저자(Author): Dodo (rabbit.white at daum dot net)
 * 파일명(FileName): BoardServiceImpl.java
 * 설명(Description):
 * 
 * 
 */

package com.smile.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smile.web.db.DBFactory;
import com.smile.web.model.Board;

public class BoardServiceImpl {
	
	private static BoardServiceImpl boardDAO;
	private static DBFactory dbPool;
	
	private BoardServiceImpl() {}
	
	public static BoardServiceImpl getInstatnce() {
		
		if ( boardDAO == null ) {
			boardDAO = new BoardServiceImpl();
			dbPool = new DBFactory();
		}
		
		return boardDAO;
		
	}
	
	public List<Board> getBoardList(long startNum, long endNum){
		
		Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Board node = null;
    	
    	List<Board> boardList = new ArrayList<Board>();

    	String sql = "select * from (select /*+INDEX_DESC(tbl_board pk_board) */ rownum rn, A.*" + 
    				 " from board A order by id desc) " + 
    				 "where rn >= ? and rn <= ?";

    	//System.out.println(sql);
    	//System.out.println(startNum + "/" + endNum);
    	
    	try {
    		conn = dbPool.getConnection();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setLong(1, startNum);
    		pstmt.setLong(2, endNum);
    		
    		rs = pstmt.executeQuery();

    		while ( rs.next() ) {
    			// 데이터가 존재할 때, 노드 생성
    			node = new Board();
    			
    			node.setId(rs.getLong(2));
    			node.setSubject(rs.getNString(3));
    			node.setMemo(rs.getNString(4));
    			node.setName(rs.getNString(5));
    			
    			boardList.add(node);
    			
    			System.out.println("rs:" + rs.getLong(1));
    			
    		}

    	}catch(Exception ex) {
    		System.out.println("오류 발생: " + ex);
    	}
    	finally {
    		dbPool.close(conn, pstmt, rs);
    	}
		
		return boardList;
		
	}
	
	public List<Board> getBoardKeywordList(String keyword, long startNum, long endNum){
		
		Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Board node = null;
    	
    	String allKeyword = "%" + keyword + "%";
    	
    	List<Board> boardList = new ArrayList<Board>();

    	String sql = "select * " + 
    				 "from (select /*+INDEX_DESC(tbl_board pk_board) */ rownum rn, A.* " + 
    				 "from board A where subject like ? order by id desc) " + 
    				 "where rn >= ? and rn <= ?";
    	
    	//System.out.println(sql);
    	
    	try {
    		conn = dbPool.getConnection();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setNString(1, allKeyword);
    		pstmt.setLong(2, startNum);
    		pstmt.setLong(3, endNum);
    		
    		rs = pstmt.executeQuery();

    		while ( rs.next() ) {
    			// 데이터가 존재할 때, 노드 생성
    			node = new Board();
    			
    			node.setId(rs.getLong(2));
    			node.setSubject(rs.getNString(3));
    			node.setMemo(rs.getNString(4));
    			node.setName(rs.getNString(5));
    			
    			boardList.add(node);
    			
    			//System.out.println("rs:" + rs.getLong(1));
    			
    		}

    	}catch(Exception ex) {
    		System.out.println("오류 발생: " + ex);
    	}
    	finally {
    		dbPool.close(conn, pstmt, rs);
    	}
		
		return boardList;
		
	}
	
	public long getTotalCount() {
		
		Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Board node = null;
    	
    	long cnt = 0;

    	String sql = "select count(*) from board";
    	
    	System.out.println("sql:" + sql);

    	try {
    		conn = dbPool.getConnection();
    		//System.out.println( conn.getSchema() );
    		pstmt = conn.prepareStatement(sql);
    		
    		rs = pstmt.executeQuery();
    		//System.out.println( rs.getFetchSize() );

    		if ( rs.next() ) {
    			cnt = rs.getLong(1);
    	    	System.out.println("전체갯수:" + cnt + "/" + rs.getLong(1));
    		}

    	}catch(Exception ex) {
    		System.out.println("오류 발생: " + ex);
    	}
    	finally {
    		dbPool.close(conn, pstmt, rs);
    	}
		
		return cnt;
		
	}
	
	public long getTotalKeywordCount(String keyword) {
		
		Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Board node = null;
    	
    	String allKeyword = "%" + keyword + "%";
    	
    	long cnt = 0;

    	String sql = "select count(*) from board where subject like ?";
    	
    	//System.out.println("sql:" + sql + "/키:" + keyword);

    	try {
    		conn = dbPool.getConnection();
    		/// System.out.println( conn.getSchema() );
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setNString(1, allKeyword);
    		
    		rs = pstmt.executeQuery();
    		/// System.out.println( rs.getFetchSize() );

    		if ( rs.next() ) {
    			cnt = rs.getLong(1);
    	    	System.out.println("특정 갯수:" + cnt + "/" + rs.getLong(1));
    		}

    	}catch(Exception ex) {
    		System.out.println("오류 발생: " + ex);
    	}
    	finally {
    		dbPool.close(conn, pstmt, rs);
    	}
		
		return cnt;
		
	}

}