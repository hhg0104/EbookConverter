<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/resource/jsp/include.jsp" flush="true" />
<script>
	function back() {
		window.history.back();
	}

	function checkFile() {

		var filePath = $("#textFile").val();
		var fileName = $("#fileName").value;

		if ((filePath == null || filePath == "") && (fileName == null || fileName == "")) {

			alert("파일이 선택되지 않았습니다.");

			return false;

		}

		var validExtensions = new Array();

		var ext = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();

		validExtensions[0] = 'txt';

		for (var i = 0; i < validExtensions.length; i++) {

			if (ext == validExtensions[i])

				return true;

		}

		alert('텍스트파일만 입력가능합니다.');

		return false;

	}
	function check() {
		if (confirm("새로운 파일은 선택하시겠습니까?")) {
			location.reload();
		} else {
			return false;
		}
	}
</script>
<meta charset="utf-8">
<title>Bibliotheca Converter</title>

</head>

<body>
	<input type="hidden" id="fileName"
		value="<%=request.getParameter("fileName")%>">
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
						<li><a class="ajax-link" href="#"
							style="background-color: #fff" onclick="return check()"><i
								class="icon-folder-open"></i><span class="hidden-tablet">
									<b><font color="#1294AB">STEP1 파일입력</font></b>
							</span></a></li>
						<li><a class="ajax-link" href="#"><i class="icon-edit"></i><span
								class="hidden-tablet"> STEP2 정보</span></a></li>
						<li><a class="ajax-link" href="#"><i
								class="icon-list-alt"></i><span class="hidden-tablet">
									STEP3 첫 라인선택</span></a></li>
						<li><a class="ajax-link" href="#"><i
								class="icon-align-justify"></i><span class="hidden-tablet">
									STEP4 문단정리</span></a></li>
						<li><a class="ajax-link" href="#"><i class="icon-picture"></i><span
								class="hidden-tablet"> STEP5 책 표지 선택</span></a></li>
						<li><a class="ajax-link" href="#"><i
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


				<div>
					<ul class="breadcrumb">
						<li><b>STEP1</b> <span class="divider">-></span></li>
						<li>STEP2 <span class="divider">-></span>
						</li>
						<li>STEP3 <span class="divider">-></span>
						</li>
						<li>STEP4 <span class="divider">-></span>
						</li>
						<li>STEP5</li>
					</ul>
				</div>

				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-folder-open"></i> STEP1 파일 입력하기
							</h2>
						</div>
						<div class="box-content">
							<form action="/book" method="POST" enctype="multipart/form-data"
								class="form-horizontal" onsubmit="return checkFile()">
								<fieldset>

									<div class="control-group">
										<label class="control-label">File Upload</label>
										<div class="controls">
											<input type="file" id="textFile" name="textFile"><br>
											<br>
										</div>
									</div>
									<div class="form-actions">
										<button type="button" class="btn" onclick="back()">Back</button>
										<button type="submit" class="btn btn-primary">Next</button>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
					<!--/span-->

				</div>
				<!--/row-->

				<!-- content ends -->
			</div>
			<!--/#content.span10-->
		</div>
		<!--/fluid-row-->

		<hr>
		<footer>
			<p class="pull-left"></p>
			<p class="pull-right"></p>
		</footer>
	</div>
</body>
</html>
