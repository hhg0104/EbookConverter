<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>epub파일 만들기</title>

<link rel="stylesheet" type="text/css" href="/EC/resource/css/layout.css" />
<link rel="stylesheet" type="text/css" href="/EC/resource/css/myfont.css" />
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/earlyaccess/nanumbrushscript.css" />
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css" />




<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="/EC/resource/js/jquery.filestyle.js"></script>
<script src="/EC/resource/jquery.gradienttext-0.1.js"></script>


<script>
(function($) {
	$(document).ready(function() {
		$("input[type=file]").filestyle({
			image : "/EC/resource/img/choose-file.gif",
			imageheight : 22,
			imagewidth : 82,
			width : 250
		});

		$("#titleGradient").gradienttext({
			colors : [ "#FFF", "#999999" ],
			style : "vertical",
			shadow : true,
			shadow_color : "#000000",
			shadow_offset_x : 1,
			shadow_offset_x : 1,
			shadow_blur : 1
		});
		$(".fields").watermarkify();
		
	});
})(jQuery);

function back(){
	window.history.back();
}
function fileOpen() {

    var filePath = $("#s_file").val();

    if(filePath.indexOf('.') == -1) {

            alert("파일이 선택되지 않았습니다.");

            return false; 

	}

		var validExtensions = new Array();

		var ext = filePath.substring(filePath.lastIndexOf('.') + 1)
				.toLowerCase();

		validExtensions[0] = 'txt';

		for (var i = 0; i < validExtensions.length; i++) {

			if (ext == validExtensions[i])

				return true;

		}

		alert('The file extension ' + ext.toUpperCase() + ' is not allowed!');

		return false;

	}
</script>

</head>

<body>
<div>
	<div class="clearfix">
    	<a href="/EC/book">Bibliotheca Converter</a>
	</div>
	