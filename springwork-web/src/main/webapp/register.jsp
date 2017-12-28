<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="UTF-8"%>
<head>
<%
	String baseUrl = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=baseUrl%>/js/jquery-3.2.1.min.js"></script>
<title>SkyNet</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#sub").click(function(){
			var userName = $("#userName").val();
			var passwd = $("#passwd").val();
			if(userName == null || userName == ""){
				
				return false;
			}
			if(passwd == null || passwd == ""){
				
				return false;
			}
			$.ajax({
				type : "post",
				contentType: 'application/json',
				url : "<%=baseUrl%>/pub/register",
				dataType : "json",
				data : "{\"userName\":\""+userName+"\",\"password\":\""+passwd+"\"}",
				success : function(data) {
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});
	});
	function ajaxTest() {
		var type = "1";
		$.ajax({
			type : "post",
			url : "<%=baseUrl%>/pub/register",
			dataType : "json",
			data : {
				reqType : type
			},
			success : function(data) {
				$("#div1").html(
						data.uuid + "<br>" + data.welMsg + "<br>"
								+ data.dateTime);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
</script>
<body>
	这里是htm1
	<div id="div1"></div>
	<form id="form" action="<%=baseUrl%>/pub/register" method="post">
		用户名:<br> <input id="userName" type="text" name="userName">
		<br> 密码:<br> <input id="passwd" type="text" name="userPwd">
		<input id="sub" type="button" value="Submit" />
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#div1").html("呵呵");
		});
	</script>
</body>
</html>
