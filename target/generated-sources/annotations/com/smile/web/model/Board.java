/*
 * 주제(Subject): Board Project (Paging) - Maven with Oracle 19, HikariCP 3.4.2	
 * 생성일자(Create Date): 2020-09-30
 * 저자(Author): Dodo (rabbit.white at daum dot net)
 * 파일명(FileName): Board.java
 * 설명(Description):
 * 
 * 
 */

package com.smile.web.model;

import java.sql.Timestamp;

public class Board {

	private long id;
	private String name;
	private String subject;
	private String memo;
	private long count;
	
	private Timestamp regidate;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public Timestamp getRegidate() {
		return regidate;
	}

	public void setRegidate(Timestamp regidate) {
		this.regidate = regidate;
	}
	
}
