<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/resource/jsp/include.jsp" flush="true" />
<meta charset="utf-8">
<title>Bibliotheca Converter</title>

<!-- The styles -->
<script>
	function back() {
		window.history.back();
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
						<li><a class="ajax-link" href="#"><i
								class="icon-folder-open"></i><span class="hidden-tablet">
									STEP1 파일입력</span></a></li>
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
						<li>STEP1 <span class="divider">-></span></li>
						<li>STEP2 <span class="divider">-></span></li>
						<li>STEP3 <span class="divider">-></span></li>
						<li>STEP4 <span class="divider">-></span></li>
						<li>STEP5 <span class="divider">-></span></li>
						<li>STEP6 <span class="divider">-></span></li>
						<li><b>Download</b></li>
					</ul>
				</div>

				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-eye-open"></i> Download
							</h2>
						</div>
						<div class="box-content">
							<form action="./download" class="form-horizontal" method="POST">
								<fieldset>
									<b> 파일 변환이 완료되었습니다. </b>
									<div class="form-actions">
										<button type="button" onclick="back()" class="btn">Back</button>
										<button type="submit" class="btn btn-primary">Download</button>
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
