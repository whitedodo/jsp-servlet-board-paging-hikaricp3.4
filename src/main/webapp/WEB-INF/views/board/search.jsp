<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="searchArea">
	<form id="searchForm" action="list.do" method='get'>
		<select name="type">
			<option value="T">제목</option>
			<option value="C">내용</option>
			<option value="W">작성자</option>
		</select>
		<input type="text" name="keyword">
		<button class="">검색</button>
	</form>
</div>