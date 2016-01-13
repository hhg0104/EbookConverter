<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script>

	function checkImgFile(form) {
		if (form.imgOption[1].checked || form.imgOption[2].checked) {
			return true;
		}
		var filePath = $("#imgFile").val();
		if (filePath.indexOf('.') == -1) {
			alert("파일이 선택되지 않았습니다.");
			return false;
		}
		var validExtensions = new Array();
		var ext = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
		validExtensions[0] = 'bmp';
		validExtensions[1] = 'jpg';
		validExtensions[2] = 'jpeg';
		validExtensions[3] = 'gif';
		validExtensions[4] = 'png';
		for (var i = 0; i < validExtensions.length; i++) {
			if (ext == validExtensions[i])
				return true;
		}
		alert(ext.toUpperCase() + ' 파일은 선택할 수 없습니다.');
		return false;
	}
	
	function back() {
		window.history.back();
	}
	
</script>
	<meta charset="utf-8">
	<title>Bibliotheca Converter</title>
	
	<!-- The styles -->
	<link id="bs-css" href="/EC/resource/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="/EC/resource/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="/EC/resource/css/charisma-app.css" rel="stylesheet">
	<link href="/EC/resource/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='/EC/resource/css/fullcalendar.css' rel='stylesheet'>
	<link href='/EC/resource/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='/EC/resource/css/chosen.css' rel='stylesheet'>
	<link href='/EC/resource/css/uniform.default.css' rel='stylesheet'>
	<link href='/EC/resource/css/colorbox.css' rel='stylesheet'>
	<link href='/EC/resource/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='/EC/resource/css/jquery.noty.css' rel='stylesheet'>
	<link href='/EC/resource/css/noty_theme_default.css' rel='stylesheet'>
	<link href='/EC/resource/css/elfinder.min.css' rel='stylesheet'>
	<link href='/EC/resource/css/elfinder.theme.css' rel='stylesheet'>
	<link href='/EC/resource/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='/EC/resource/css/opa-icons.css' rel='stylesheet'>
	<link href='/EC/resource/css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="/EC/resource/img/favicon.ico">
		
</head>

<body>
	<!-- topbar starts -->
	<div class="navbar" style="height: 150px">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="brand" href="/EC/main" style="font-size: 30px; height: 60px; margin-top:20px"> <span>Bibliotheca_Converter</span></a>
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
						<li><a class="ajax-link" href="#" style=" background-color:#fff "><i
								class="icon-picture"></i><span class="hidden-tablet">
									<b><font color="#1294AB">STEP5 책 표지 선택</font></b></span></a></li>
						<li><a class="ajax-link" href="#"><i
								class="icon-eye-open"></i><span class="hidden-tablet">
									STEP6 선택한 옵션</span></a></li>
						<li class="nav-header hidden-tablet"></li>
						<li><a class="ajax-link" href="../option/confirm"><i class="icon-star"></i><span class="hidden-tablet">CONVERSION</span></a></li>
					</ul>
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->
			
			<div id="content" class="span10">
			<!-- content starts -->
			

				<div>
					<ul class="breadcrumb">
						<li>STEP1 <span class="divider">-></span></li>
						<li>STEP2 <span class="divider">-></span></li>
						<li>STEP3 <span class="divider">-></span></li>
						<li>STEP4 <span class="divider">-></span></li>
						<li><b>STEP5</b> <span class="divider">-></span></li>
						<li>STEP6 <span class="divider">-></span></li>
						<li>Download</li>
					</ul>
				</div>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i> STEP5 책 표지 이미지 선택 </h2>
					</div>
					<div class="box-content">
						<form action="" method="POST" class="form-horizontal" enctype="multipart/form-data" onsubmit="return checkImgFile(this)">
							<fieldset>
							
							<div class="control-group">
								 <label class="radio">
									<input type="radio" name="imgOption" id="optionsRadios1" value="selection" checked="checked">
									<b>이미지 선택</b>
									<input type="file" id="imgFile" name="imgFile">
								  </label>
								  <div style="clear:both"></div>
								  <label class="radio">
									<input type="radio" name="imgOption" id="optionsRadios2" value="default">
									<b>기본 이미지</b>
									<img src="/EC/resource/img/DefaultCover.jpg" width="200" height="200" >
								  </label>
								  <div style="clear:both"></div>
								  <label class="radio">
									<input type="radio" name="imgOption" id="optionsRadios3" value="none">
									<b>표지 없음</b>
								  </label>
							</div>
						
							  <div class="form-actions">
								<button type="button" class="btn" onclick="back()">Back</button>
								<button type="submit" class="btn btn-primary">Next</button>
							  </div>
							</fieldset>
						</form>
					</div>
				</div><!--/span-->

			</div><!--/row-->
    
					<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
				
		<hr>

		<footer>
			<p class="pull-left">&copy; <a href="http://FormalWorks.com" target="_blank">FormalWorks</a> 2013</p>
			<p class="pull-right">Powered by: <a href="http://FormalWorks.com">FormalWorks</a></p>
		</footer>
		
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="/EC/resource/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="/EC/resource/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="/EC/resource/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="/EC/resource/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="/EC/resource/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="/EC/resource/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="/EC/resource/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="/EC/resource/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="/EC/resource/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="/EC/resource/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="/EC/resource/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="/EC/resource/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="/EC/resource/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="/EC/resource/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="/EC/resource/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="/EC/resource/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='/EC/resource/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='/EC/resource/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="/EC/resource/js/excanvas.js"></script>
	<script src="/EC/resource/js/jquery.flot.min.js"></script>
	<script src="/EC/resource/js/jquery.flot.pie.min.js"></script>
	<script src="/EC/resource/js/jquery.flot.stack.js"></script>
	<script src="/EC/resource/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="/EC/resource/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="/EC/resource/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="/EC/resource/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="/EC/resource/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="/EC/resource/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="/EC/resource/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="/EC/resource/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="/EC/resource/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="/EC/resource/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="/EC/resource/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="/EC/resource/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="/EC/resource/js/charisma.js"></script>
	
		
</body>
</html>
