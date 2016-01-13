<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../jsp/header.jsp"  %>
<%@ include file="../jsp/menu.jsp"  %>
	<h3>책 정보 확인하기</h3>
	<form action="/EC/book/<%=session.getAttribute("bookId")%>/meta" onSubmit="" class="margin_file" method="POST">
		<h4>제목</h4><br><input type=text class = "fields" name=title value="${title}"><br> 
		<h4>저자</h4><br><input type=text class = "fields" name=author value="${author}"><br>
		<input type = "button" class="button" value="수정" onclick="" >
		<br>
	
		
		<input type = "button" class="button" value="처음으로" onclick="location.href='/EC/book'" ><input type = "button" class="button" value="이전" onclick="back()" ><input type = "submit" class="button" value="다음">
	</form>
	
	
	
<%@ include file="../jsp/footer.jsp"  %>
</body>

</html>
