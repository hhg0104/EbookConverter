<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../jsp/header.jsp"  %>
<%@ include file="../jsp/button.jsp"  %>


	<h2>첫 문단 찾기</h2>
	첫문단의 줄수를 찾아주세요.  
	<form action="option" onSubmit="" method="post">
		<input type=text name="startLine" value="${startLine}"/>
		<input type=submit value='확인'/>
	</form>

	<textarea rows="20" cols="80" name =sampleText >${sample}</textarea>
	
	<br>
	<input type = "button" value="이전" onclic="" ><input type = "button" value="다음">
<%@ include file="../jsp/footer.jsp"  %>
</body>

</html>
