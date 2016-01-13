<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/resource/jsp/include.jsp" flush="true" />
<meta charset="utf-8">
<title>Bibliotheca Converter</title>
<script>
	function back() {
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
						<li><a class="ajax-link" href="#" onclick="check()"><i
								class="icon-folder-open"></i><span class="hidden-tablet">
									STEP1 파일입력</span></a></li>
						<li><a class="ajax-link" href="#"
							style="background-color: #fff"><i class="icon-edit"></i><span
								class="hidden-tablet"> <b><font color="#1294AB">STEP2
											정보</font></b></span></a></li>
						<li><a class="ajax-link" href="./option/startLine"><i
								class="icon-list-alt"></i><span class="hidden-tablet">
									STEP3 첫 라인선택</span></a></li>
						<li><a class="ajax-link" href="./option/arrangement"><i
								class="icon-align-justify"></i><span class="hidden-tablet">
									STEP4 문단정리</span></a></li>
						<li><a class="ajax-link" href="./option/coverImg"><i
								class="icon-picture"></i><span class="hidden-tablet">
									STEP5 책 표지 선택</span></a></li>
						<li><a class="ajax-link" href="./option/confirm"><i
								class="icon-eye-open"></i><span class="hidden-tablet">
									STEP6 선택한 옵션</span></a></li>
						<li class="nav-header hidden-tablet"></li>
						<li><a class="ajax-link" href="./option/confirm"><i
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
						<li><b>STEP2</b> <span class="divider">-></span></li>
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
								<i class="icon-edit"></i> STEP2 제목&저자 확인하기
							</h2>
						</div>
						<div class="box-content">
							<form action="" method="POST" class="form-horizontal">
								<fieldset>

									<div class="control-group">
										<label class="control-label" for="appendedInputButton">title</label>
										<div class="controls">
											<div class="input-append">
												<input id="appendedInputButton" size="16" name="title"
													type="text" value="${title}">
												<!-- <button class="btn" type="button">edit</button> -->
											</div>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="appendedInputButton">author</label>
										<div class="controls">
											<div class="input-append">
												<input id="appendedInputButton" size="16" name="author"
													type="text" value="${author}">
												<!-- <button class="btn" type="button">edit</button> -->
											</div>
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
