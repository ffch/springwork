<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="UTF-8"%> 
<head>
<%
	String baseUrl = request.getContextPath();
	String clientId = request.getParameter("clientId");
	System.out.println("clientId:"+clientId );
	String userName = request.getParameter("userName");
	String userPwd = request.getParameter("userPwd");
	if(userName == null || "".equals(userName) 
		||userPwd == null || "".equals(userPwd) ){
		response.sendRedirect("/login.jsp?clientId="+clientId);
	}
	System.out.println(userName + "+++" +userPwd );
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=baseUrl%>/js/jquery-3.2.1.min.js"></script>
<title>SkyNet</title>
</head>
<script type="text/javascript">
	function ajaxTest() {
		var clientId = "<%=clientId%>";
		var userName = "<%=userName%>";
		var userPwd = "<%=userPwd%>";
		$.ajax({
			type : "post",
			url : "<%=baseUrl%>/oauth/token",
			dataType : "json",
			data : {grant_type : "password",username : userName,password : userPwd} ,
			beforeSend:function(xhr){
				xhr.setRequestHeader('Authorization', "Basic "+ clientId);
			},
			success : function(data) {
				window.location.replace("/index.html?"+data.access_token+"&"+data.refresh_token);
				$("#div1").html(data.access_token + "<br>" + 
						data.refresh_token + "<br>"+
						data.expires_in);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	function ajaxTest1() {
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
</script>
<body>
	这里是htm1 
	<div id="div1"></div>
	<button type="button" onclick="ajaxTest1()">Welcome</button>
	<button type="button" onclick="ajaxTest()">授权</button>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#div1").html("呵呵");
		});
	</script>
</body>
</html>
