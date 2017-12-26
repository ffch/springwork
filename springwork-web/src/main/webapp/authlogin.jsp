<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<head>
<%
	String baseUrl = request.getContextPath();
	AuthenticationException authenticationException = (AuthenticationException)request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
	String errorMsg = "";
	if(authenticationException != null){
		errorMsg = authenticationException.getMessage();
	}
	System.out.println(errorMsg);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=baseUrl%>/js/jquery-3.2.1.min.js"></script>
<title>SkyNet</title>
</head>
<script type="text/javascript">
	function ajaxTest() {
		var type = "1";
		$.ajax({
			type : "post",
			url : "<%=baseUrl%>/test/welCome",
			dataType : "json",
			data : {reqType : type} ,
			success : function(data) {
				$("#div1").html(data.uuid + "<br>" + 
						data.welMsg + "<br>"+
						data.dateTime);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	
	function genCode() {
		$.ajax({
			type : "post",
			url : "<%=baseUrl%>/test/imgtoken",
			dataType : "json",
			data : {} ,
			success : function(data) {
				$("#code").html(data);
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
	<button type="button" onclick="ajaxTest()">Welcome</button>
	<form action="<%=baseUrl%>/login" method="post">
First name:<br>
<input type="text" name="userName">
<br>
Last name:<br>
<input type="text" name="userPwd">
<br>
验证码:<br>
<input type="text" name="imgtoken"><span id="code" onclick="genCode()"></span>
<input type="submit" value="Submit" />
</form>
<span id="error"></span>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#div1").html("呵呵");
			genCode();
			$("#error").html("<%=errorMsg%>");
		});
	</script>
</body>
</html>
