<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/resource/jsp/include.jsp" flush="true" />
<meta charset="utf-8">
<title>Bibliotheca Converter</title>

<script>
	function goBack() {
		window.history.back();
	}
	function check() {
		if (confirm("파일을 새로 입력하시겠습니까")) {
			location.href = "/book";
			return true;
		} else {
			return false;
		}
	}
	function checkNum() {
		var value = document.getElementById('appendedInputButton').value;
		if (isNaN(value) || value == null || value == "" || value > 150) {
			alert("150줄 이내의 '숫자'를 입력해주십시오.");
			return false;
		}
	}
</script>
</head>

<body>
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
						<li><a class="ajax-link" href="/book"
							onclick="return check()"><i class="icon-folder-open"></i><span
								class="hidden-tablet"> STEP1 파일입력</span></a></li>
						<li><a class="ajax-link" href="../meta"><i
								class="icon-edit"></i><span class="hidden-tablet"> STEP2
									정보</span></a></li>
						<li><a class="ajax-link" href="#"
							style="background-color: #fff"><i class="icon-list-alt"></i><span
								class="hidden-tablet"> <b><font color="#1294AB">STEP3
											첫 라인선택</font></b></span></a></li>
						<li><a class="ajax-link" href="./arrangement"><i
								class="icon-align-justify"></i><span class="hidden-tablet">
									STEP4 문단정리</span></a></li>
						<li><a class="ajax-link" href="./coverImg"><i
								class="icon-picture"></i><span class="hidden-tablet">
									STEP5 책 표지 선택</span></a></li>
						<li><a class="ajax-link" href="./confirm"><i
								class="icon-eye-open"></i><span class="hidden-tablet">
									STEP6 선택한 옵션</span></a></li>
						<li class="nav-header hidden-tablet"></li>
						<li><a class="ajax-link" href="./confirm"><i
								class="icon-star"></i><span class="hidden-tablet">CONVERSION</span></a></li>
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
						<li>STEP1 <span class="divider">-></span>
						</li>
						<li>STEP2 <span class="divider">-></span>
						</li>
						<li><b>STEP3</b> <span class="divider">-></span></li>
						<li>STEP4 <span class="divider">-></span>
						</li>
						<li>STEP5</li>
					</ul>
				</div>

				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-list-alt"></i> STEP3 첫 문단 찾기
							</h2>
						</div>
						<div class="box-content">
							<form action="" class="form-horizontal" method="POST"
								onsubmit="return checkNum();">
								<fieldset>
									<b>문단 정리가 필요한 첫 문단의 라인넘버를 입력해 주십시오.<br> (그 문단에 소제목이
										있다면, 그 소제목의 라인넘버를 입력해 주십시오.)
									</b><br>
									<div class="control-group">

										<div class="controls">
											<div class="input-append">
												<input id="appendedInputButton" size="16"
													value="${startLine}" type="text" name="startLine">번째
												라인
											</div>
										</div>
									</div>
									<div class="control-group"></div>
									<textarea class="" id="textarea2" rows="13" cols="300"
										style="width: 700px">${sample}</textarea>
									<div class="form-actions">
										<button type="button" class="btn" onclick="goBack()">Back</button>
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
