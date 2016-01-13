<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../jsp/header.jsp"%>
<%@ include file="../jsp/button.jsp"%>


<script>
	
	function getPreview(){
		var option=document.getElementsByName("option");
		for(var i = 0, length = option.length; i < length; i++){
			if(option[i].checked){
			var URL="/EbookConverter/paragraph/preview?option="+option[i].value;
				window.open(URL,"", "height=900, width=800, scrollbars=1, resizeable=yes, left=200");
				break;
			}
		}
	}
</script>

<h2>문단구분 설정하기</h2>
<form action="settingEnd" method="POST">
	<input type="radio" name="option" value="origin" checked="checked">원본(default)<br>
	<input type="radio" name="option" value="period">마침표<br>
	<input type="radio" name="option" value="indent">들여쓰기<br>
	<input type="radio" name="option" value="blank">빈줄기준<br> 
	<input type="button" value="이전" onclick="">
	<input type="button" value="미리보기" onclick="getPreview()">
	<input type="submit" value="다음">
</form>
<br>
<br>
<br>
<br>
<br>
<br>

<%@ include file="../jsp/footer.jsp"%>
</body>

</html>
