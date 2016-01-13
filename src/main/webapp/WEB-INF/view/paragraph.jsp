<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/resource/jsp/include.jsp" flush="true" />
<meta charset="utf-8">
<title>Bibliotheca Converter</title>
<script type="text/javascript">
	function back() {
		window.history.back();
	}
	function kuisin(idMyDiv) {
		var objDiv = document.getElementsByName("optionsRadios");
		for (var i = 0; i < objDiv.length; i++) {
			var option = objDiv[i].value;
			var optionDiv = document.getElementById(option);
			if (option == idMyDiv) {
				optionDiv.style.display = "block";
			} else {
				optionDiv.style.display = "none";
			}
		}
	}
	function check() {
		if (confirm("파일을 새로 입력하시겠습니까")) {
			location.href = "/book";
			return true;
		} else {
			return false;
		}
	}

	function isSelected() {
		var options = document.getElementsByName("optionsRadios");
		for (var i = 0; i < options.length; i++) {
			var option = options[i].checked;
			if (option == true) {
				return true;
			}
		}
		alert("옵션 중 하나를 선택하여 주십시오.");
		return false;
	}
</script>
</head>

<body>
	<input type="hidden" id="selectedOption" value="${option}">
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
								class="icon-edit"></i> <span class="hidden-tablet"> STEP2
									정보</span></a></li>
						<li><a class="ajax-link" href="./startLine"><i
								class="icon-list-alt"></i><span class="hidden-tablet">
									STEP3 첫 라인선택</span></a></li>
						<li><a class="ajax-link" href="./arrangement"
							style="background-color: #fff"><i class="icon-align-justify"></i>
								<span class="hidden-tablet"> <b><font color="#1294AB">STEP4
											문단정리</font></b></span></a></li>
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
						<li>STEP3 <span class="divider">-></span>
						</li>
						<li><b>STEP4</b> <span class="divider">-></span></li>
						<li>STEP5</li>
					</ul>
				</div>

				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-align-justify"></i> STEP4 문단구분 설정하기
							</h2>
						</div>
						<div class="box-content">
							<form action="./arrangement" method="POST"
								class="form-horizontal" onsubmit="return isSelected();">
								<fieldset>

									<div>
										<div>
											<script>
												function optionChecked() {
													/* var optionParam=document.getElementById("selectedOption");
													var options=document.getElementsByName("optionsRadios"); */
													/* alert(document.getElementById("optionsRadios1").checked);
													document.getElementById("optionsRadios1").checked='true'; */
													alert(document.getElementById("optionsRadios1").checked);
													document.getElementById("optionsRadios1").checked = true;
												}
											</script>
											<button class="btn" onclick="kuisin('origin'); return false;">미리보기</button>
											<label class="radio"> <input type="radio"
												name="optionsRadios" id="optionsRadios1" value="origin">
												<b>원본(default)</b>
											</label>
											<div style="clear: both"></div>
											<button class="btn" onclick="kuisin('period'); return false;">미리보기</button>
											<label class="radio"> <input type="radio"
												name="optionsRadios" id="optionsRadios2" value="period">
												<b>마침표</b>
											</label>
											<div style="clear: both"></div>
											<button class="btn" onclick="kuisin('indent'); return false;">미리보기</button>
											<label class="radio"> <input type="radio"
												name="optionsRadios" id="optionsRadios3" value="indent">
												<b>들여쓰기</b>
											</label>
											<div style="clear: both"></div>
											<button class="btn" onclick="kuisin('blank'); return false;">미리보기</button>
											<label class="radio"> <input type="radio"
												name="optionsRadios" id="optionsRadios4" value="blank">
												<b>빈줄</b>
											</label>
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

				<div id="origin" class="row-fluid sortable" style="display: none">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>원본(default)</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">${previewContents.get("origin")}</div>

					</div>
					<!--/span-->
				</div>
				<!--/row-->

				<div id="period" class="row-fluid sortable" style="display: none">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>마침표</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">${previewContents.get("period")}</div>

					</div>
					<!--/span-->
				</div>
				<!--/row-->

				<div id="indent" class="row-fluid sortable" style="display: none">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>들여쓰기</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">${previewContents.get("indent")}</div>

					</div>
					<!--/span-->
				</div>
				<!--/row-->

				<div id="blank" class="row-fluid sortable" style="display: none">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>빈줄</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">${previewContents.get("blank")}</div>

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