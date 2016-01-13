<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>

<script>
function check() {
	if(confirm("파일을 새로 입력하시겠습니까")) {
		location.href = "/book";
		return true;
	} else {
		return false;
	}
}
</script>
<meta charset="utf-8">
<title>Bibliotheca Converter</title>
<jsp:include page="/resource/jsp/include.jsp" flush="true" />

</head>

<body>
	<input type="hidden" id="fileName"
		value="<%=session.getAttribute("bookId")%>">
	<!-- topbar starts -->
	<div class="navbar" style="height: 150px">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="brand" href="/main"
					style="font-size: 30px; height: 60px; margin-top: 20px"> <span>Bibliotheca_Converter</span></a>
			</div>
		</div>
	</div>
	<!-- topbar ends -->
	<div class="container-fluid">
		<div class="row-fluid">

			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">Main</li>
						<li><a class="ajax-link" href="/book"><i
								class="icon-folder-open"></i><span class="hidden-tablet">
									STEP1 파일입력</span></a></li>
						<li><a class="ajax-link" href="#" onclick="return check()"><i class="icon-edit"></i><span
								class="hidden-tablet"> STEP2 정보</span></a></li>
						<li><a class="ajax-link" href="#" onclick="return check()"><i
								class="icon-list-alt"></i><span class="hidden-tablet">
									STEP3 첫 라인선택</span></a></li>
						<li><a class="ajax-link" href="#" onclick="return check()"><i
								class="icon-align-justify"></i><span class="hidden-tablet">
									STEP4 문단정리</span></a></li>
						<li><a class="ajax-link" href="#" onclick="return check()"><i
								class="icon-picture"></i><span class="hidden-tablet">
									STEP5 책 표지 선택</span></a></li>
						<li><a class="ajax-link" href="#" onclick="return check()"><i
								class="icon-eye-open"></i><span class="hidden-tablet">
									STEP6 선택한 옵션</span></a></li>
						<li class="nav-header hidden-tablet"></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<!-- left menu ends -->

			<div id="content" class="span10">
				<!-- content starts -->
				<div class="row-fluid">
					<div class="box span12">
						<div class="box-header well">
							<h2>
								<i class="icon-info-sign"></i> Introduction
							</h2>
						</div>
						<div class="box-content">
							<h1>
								Bibliotheca Converter <small>(TEXT TO EPUB)</small>
							</h1>
							<p>TEXT파일을 EPUB파일로 변환하는 웹프로그램입니다.</p>
							<p>
								<b>시작하려면 File Upload Page 버튼을 누르세요.</b>
							</p>


							<p class="left">
								<a href="/book" class="btn btn-large btn-primary">File
									Upload Page</a>
							</p>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>



				<!-- content ends -->
			</div>
			<!--/#content.span10-->
		</div>
		<!--/fluid-row-->

		<hr>

		<footer>
			<p class="pull-left">
			</p>
			<p class="pull-right">
			</p>
		</footer>

	</div>
	

</body>
</html>

