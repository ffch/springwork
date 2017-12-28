<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/css/page.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.validate.min.js" ></script>
<script type="text/javascript" src="${ctx}/js/login-regis.js" ></script>
<script type="text/javascript">
var passportUrl = '${ctx}';
var operatype = "<%=request.getParameter("operatype")%>"; 
function toIndex(){
	window.location.href= passportUrl + "/index_v2.jsp";
}
</script>
<title>注册/登陆</title>
</head>
<body>
<div class="bglogin">
		<div>
			<div style="display:inline;">
				<img class="imglogocm" alt="" src="${ctx}/img/logo.png" onclick="toIndex()">
			</div>
			<div style="display:inline;float:right">
				<img class="imglogoright" alt="" src="${ctx}/img/logoright.png">
			</div>
		</div>
	<div class="loginSection">
		<p class="title" id="titlebar">使用手机注册</p>
		<form id="login-regis" novalidate="novalidate">
		    <input type="hidden" name="inviterId" value="">
			<ul>
				<li>
					<input class="account" operatype="register" id="loginId" name="loginId" type="text" autocomplete="off" placeholder="请输入常用手机号">
					<p class="error_mess"> &nbsp</p>
				</li>
				<li>
					<input class="passw" id="password" name="password" type="password" autocomplete="off" placeholder="请输入密码">
					<p class="error_mess"> &nbsp</p>
					<span class="eye"></span>
					<div style="clear: both;"></div>
				</li>
				
			</ul>
			<div class="text-center clearB"><a href="javascript:void(0);" class="agreen-regis" id="regisBtn">同意并注册</a></div>
		</form>
		<div class="already"><a id="anotherBtn" href="${ctx}/login_v2.jsp?operatype=login">已有账号，点击登录</a></div>
	</div>
</div>
</body>
<script type="text/javascript">
var operatype = "<%=request.getParameter("operatype")%>"; 
if(operatype=='login'){
	$("#titlebar").text("登陆");
	$("#regisBtn").text("登陆");
	$("#anotherBtn").text("没有账号？点击注册 ");
	$("#anotherBtn").attr("href","${ctx}/login_v2.jsp");
	$("#loginId").attr("operaType","login");
}else{
	$("#titlebar").text("使用手机注册");
	$("#regisBtn").text("同意并注册");
	$("#anotherBtn").text("已有账号，点击登录");
	$("#anotherBtn").attr("href","${ctx}/login_v2.jsp?operatype=login");
	$("#loginId").attr("operaType","register");
}
</script>
</html>