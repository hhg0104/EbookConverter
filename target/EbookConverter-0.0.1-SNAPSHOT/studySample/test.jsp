<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function testGetName(){
		var elm=document.getElementById("q");
		var eld_id=elm.getAttribute("id");
		for(var a=0;a<eld_id.length;i++){
			alert(eld_id[a]);
		}
	}
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<input type="button" name="a" id="q">
<input type="button" name="b" id="q">
<input type="button" name="c" id="q">
<input type="button" name="d" id="q">
</body>
</html>