<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "java.util.*" %>
<%@ page import = "com.smile.web.model.*" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
<style>
	@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800');

</style>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	List<Board> boardList = (List<Board>)request.getAttribute("list");
%>

<h3>게시물 목록</h3>

<!-- 목록 출력 -->
<table class="board_list">
	<tr>
		<th style="width:15%;">
			번호(Num)
		</th>
		<th>
			제목(Subject)
		</th>
		<th style="width:13%;">
			글쓴이(Author)
		</th>
		<th style="width:13%; border-right:none;">
			조회수(Count)
		</th>
	</tr>
	<%
		for(Board board:boardList){
	%>
	<tr>
		<td style="width:15%;">
			<%= board.getId() %>
		</td>
		<td>
			<%= board.getSubject() %>
		</td>
		<td style="width:13%;">
			<%= board.getName() %>
		</td>
		<td style="width:13%; border-right:none;">
			<%= board.getCount() %>
		</td>
	</tr>
	<%
		}
	%>
	
</table>

<!-- 페이징 -->
<jsp:include page="/WEB-INF/views/board/paging.jsp">
	<jsp:param name="customURL" value="${pagingUrl}" />
    <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
    <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
    <jsp:param name="startPageNo" value="${paging.startPageNo}" />
    <jsp:param name="pageNo" value="${paging.pageNo}" />
    <jsp:param name="endPageNo" value="${paging.endPageNo}" />
    <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
    <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
</jsp:include>

<!-- 검색 -->
<jsp:include page="/WEB-INF/views/board/search.jsp" />

</body>
</html>