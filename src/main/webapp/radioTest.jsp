<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript">

	function checkRadio(){
		var radios=document.getElementsByName("c");
		for(var i=0; i<radios.length; i++){
			if(radios[i].value=="origin"){
				radios[i].checked=true;
			}
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Insert title here</title>
</head>

<body>
<input type="radio" name="c" id="a" value="origin"/>
<input type="radio" name="c" id="b" value="indent"/>
<input type="button" onclick="checkRadio()"/>
</body>
</html>
