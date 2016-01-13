<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta charset="EUC-KR">

<title>Insert title here</title>

<script type="text/javascript">
	

$('#idUploadLogoButton').on('click', function () {
	 var form = new FormData(document.getElementById('idUploadLogoForm'));
	 $.ajax({
	  url: "upload-logo.htm",
	  data: form,
	  dataType: 'text',
	  processData: false,
	  contentType: false,
	  type: 'POST',
	  success: function (response) {
	      var data = jQuery.parseJSON(response);   
	      $('#idImagePlaceHolder').html('<img src="data:image/png;base64,' + data.JsonText.image + '"/>');   
	  },
	  error: function (jqXHR) {
	   //Error handling
	  }
	 });
	});

</script>


</head>

<body>

	<form id="idUploadLogoForm" enctype="multipart/form-data">

		<div>

			<label>Logo:</label>

			<div id="idImagePlaceHolder"></div>

		</div>



		<div>

			<label for="idLogoFile">Choose your logo:</label> <input
				id="idLogoFile" type="file" name="logo" />

		</div>

	</form>

	<button id="idUploadLogoButton">Upload Logo</button>

</body>

</html>