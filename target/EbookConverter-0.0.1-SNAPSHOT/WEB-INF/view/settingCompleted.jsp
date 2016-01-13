<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../jsp/header.jsp"  %>
<%@ include file="../jsp/menu.jsp"  %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>
	function confirmConversion() {
		var result = confirm("변환하시겠습니까?");
		
		if(result==true) 
			location.replace("../conversion");
		else 
			return;
						
	}
</script>

</head>
<body>

	<h3>설정한 옵션 확인하기</h3>
	<form action="../conversion" class="margin_file"><br>
		<h4>제목 </h4><input type=text value="${title}"><br><br> 
		<h4>저자 </h4><input type=text value="${author}"><br><br>
		<h4>문단 </h4><input type=text value="${option}"><br><br><br>
		<input type ="button" class="button" value="이전" onclick="back()" >
		<input type ="button" class="button" value="변환" onclick="confirmConversion()">
	</form>
	
	<br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br>
	
<%@ include file="../jsp/footer.jsp"  %>

</body>
</html>