<html xmlns="http://www.w3.org/1999/xhtml">
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>

<%@ include file="/common/head.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="${ctx}/css/page.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/menu.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/bootstrap/bootstrap-theme.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/css/bootstrap/bootstrap.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet" />

<script type="text/javascript"
	src="${ctx}/js/jquery/jquery-3.2.1.min.js"></script>
<title>模板网站</title>
</head>
<body>
	<div>
		<script type="text/javascript" color="0,0,255" opacity="0.7"
			zindex="-2" count="99" src="${ctx}/js/canvas/canvas-nest.min.js">
</script>
	</div>
	<div>
		<div id="sidebar">
			<ul>
				<li id="prof" class="item"><span
					class="glyphicon glyphicon-user"></span>
					<div>我</div></li>
				<li id="asset" class="item"><span
					class="glyphicon glyphicon-user"></span>
					<div>资产</div></li>
				<li id="brand" class="item"><span
					class="glyphicon glyphicon-user"></span>
					<div>产品</div></li>
				<li id="foot" class="item"><span
					class="glyphicon glyphicon-user"></span>
					<div>足迹</div></li>
				<li id="qqchat" class="item"><span
					class="glyphicon glyphicon-user"></span>
					<div>QQ</div></li>
				<li id="webchat" class="item"><span
					class="glyphicon glyphicon-user"></span>
					<div>语聊</div></li>
				<li id="suggest" class="item"><span
					class="glyphicon glyphicon-user"></span>
					<div>支持</div></li>
			</ul>
			<div id="closeBar">
				<span class="glyphicon glyphicon-remove"></span>
			</div>
		</div>
		<div class="nav-content" id="prof-content">
			<div class="nav-con-close">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</div>
			<div>我</div>
			<br> <a class="qqconnect"
				href="${ctx}/jsp_v2/suggestion/applylist.jsp">我的申请</a> <br> <br>
			<a class="qqconnect" href="${ctx}/jsp_v2/suggestion/watinglist.jsp">我的待办</a>
		</div>
		<div class="nav-content" id="asset-content">
			<div class="nav-con-close">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</div>
			<div>资产</div>
		</div>
		<div class="nav-content" id="brand-content">
			<div class="nav-con-close">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</div>
			<div>产品</div>
		</div>
		<div class="nav-content" id="foot-content">
			<div class="nav-con-close">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</div>
			<div>足迹</div>
		</div>
		<div class="nav-content" id="qqchat-content">
			<div class="nav-con-close">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</div>
			<div>QQ交谈</div>
			<br> <a class="qqconnect"
				href="http://sighttp.qq.com/msgrd?v=1&uin=916881512">客服1</a> <br>
			<br> <a class="qqconnect"
				href="tencent://Message/?Uin=528904035&websiteName=q-zone.qq.com&Menu=yes"><img
				border="0" SRC="http://wpa.qq.com/pa?p=1:1360011:14" alt="点击这里给我发消息"></a>
			<br>
			<br> <a class="qqconnect" target="_blank"
				href="http://wpa.qq.com/msgrd?v=3&uin=528904035&site=qq&menu=yes"><img
				border="0" src="http://wpa.qq.com/pa?p=2:123456789:51"
				alt="点击这里给我发消息" title="点击这里给我发消息" /></a>
		</div>
		<div class="nav-content" id="webchat-content">
			<div class="nav-con-close">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</div>
			<div>WebChat</div>
			<br>
			<div id="recomindUsers"></div>
		</div>
		<div class="nav-content" id="suggest-content">
			<div class="nav-con-close">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</div>
			<div>支持建议</div>
		</div>
	</div>
	<div>
		<div>
			<div style="display: inline">
				<img class="imglogo" alt="" src="${ctx}/img/logo.png">
			</div>
			<!-- 			<div style="display:inline;float:right"> -->
			<%-- 				<p><span style="font-size:14px"><a id="loginRemide" href="${ctx}/jsp/user/login.jsp?operatype=login">已有账号？登陆</a></span></p> --%>
			<!-- 			</div> -->
			<div id="lr_systembtn" class="lr_systembtn">
				<a href="#" class="lr_abtn" id="loginRemide"><span>为您提供</span></a>
				<div id="lr_menu" class="lr_menu">
					<dl>
						<dt>
							<a href="${ctx}/jsp_v2/user/userManager.jsp" target="_blank">个人管理</a>
						</dt>
					</dl>
					<dl>
						<dt>
							<a href="#" onclick="loginOut()">登出</a>
						</dt>
					</dl>
				</div>
			</div>
			<div style="display: inline; float: right">
				<img class="imglogoright" alt="" src="${ctx}/img/logoright.png">
			</div>
			<div style="display: inline; float: right">
				<img class="imglogoright" alt="" src="${ctx}/img/logoright.png">
			</div>
		</div>
		<div class="frontindex">
			<div class="bgindex">
				<div>
					<span style="font-size: 30px"> <span
						style="font-family: 微软雅黑; margin: 550px;"> <span
							style="color: rgb(255, 255, 255);">为您创造价值 </span>
					</span>
					</span>
				</div>
				<div>
					<img class="registerfront" alt="" src="${ctx}/img/register.png"
						onclick="register();">
				</div>
				<div>
					<img class="bgfront" alt="" src="${ctx}/img/index1001.png">
				</div>
			</div>

		</div>

	</div>


	<script type="text/javascript" src="${ctx}/js/sidebar.js"></script>
</body>

<script type="text/javascript">
	var appUrl = '${ctx}';
    var loginUrl = appUrl + "/login_v2.jsp?operatype=login";
    var loginflag = false;
    var loginRemide = "已有账号？登陆";
    var typeUser ="0000";
    $.ajax({
		type : "post",
		url : "${ctx}/pub/getUser",
		data : {} ,
		dataType : "text",
		success : function(data) {
			console.log(data);
			if(data != null && data != ""){
				loginRemide = data;
				loginflag = true;
				typeUser = "1001";
				if(!loginflag){
			    	$("#loginRemide").text(loginRemide);
			    	$("#loginRemide").attr("href",loginUrl);
			    }else{
			    	loginUrl = appUrl + "/index_v2.jsp";
			    	$("#loginRemide").text(loginRemide);
			    	$("#loginRemide").attr("href",loginUrl);
			    }
				if(loginflag){
					var lr_systembtn = $("#lr_systembtn");
					var lr_menu = $("#lr_menu");
					lr_systembtn.mouseenter(function(){
						t_delay= setTimeout(function(){
							lr_menu.fadeIn("slow");
						},200);
					});
					lr_systembtn.mouseleave(function(){
						clearTimeout(t_delay);
						lr_menu.fadeOut("slow");
					});
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
		}
	});
    console.log(loginflag);
    console.log(loginRemide);
    console.log(loginUrl);
    if(!loginflag){
    	$("#loginRemide").text(loginRemide);
    	$("#loginRemide").attr("href",loginUrl);
    }else{
    	loginUrl = appUrl + "/index.jsp";
    	$("#loginRemide").text(loginRemide);
    	$("#loginRemide").attr("href",loginUrl);
    }
    
	function register(){
		window.location.href="${ctx}/login.jsp";
	}
	
	if(loginflag){
		var lr_systembtn = $("#lr_systembtn");
		var lr_menu = $("#lr_menu");
		lr_systembtn.mouseenter(function(){
			t_delay= setTimeout(function(){
				lr_menu.fadeIn("slow");
			},200);
		});
		lr_systembtn.mouseleave(function(){
			clearTimeout(t_delay);
			lr_menu.fadeOut("slow");
		});
	}
	
	function loginOut(){
		 $.ajax({
				type : "post",
				url : "${ctx}/logout",
				data : {} ,
				dataType : "text",
				success : function(data) {
						loginRemide = "已有账号？登陆";
						loginflag = false;
						typeUser = "0000";
						if(!loginflag){
					    	$("#loginRemide").text(loginRemide);
					    	$("#loginRemide").attr("href",loginUrl);
					    }else{
					    	loginUrl = appUrl + "/index_v2.jsp";
					    	$("#loginRemide").text(loginRemide);
					    	$("#loginRemide").attr("href",loginUrl);
					    }
						if(loginflag){
							var lr_systembtn = $("#lr_systembtn");
							var lr_menu = $("#lr_menu");
							lr_systembtn.mouseenter(function(){
								t_delay= setTimeout(function(){
									lr_menu.fadeIn("slow");
								},200);
							});
							lr_systembtn.mouseleave(function(){
								clearTimeout(t_delay);
								lr_menu.fadeOut("slow");
							});
						}
				}
			});
	}
	
// 	$.ajax({
// 		type : "post",
// 		url : "${ctx}/user/usertype",
// 		data : {userType : "1001"} ,
// 		dataType : "json",
// 		success : function(data) {
// 			var users = data.users;
// 			if(users != null && users.length != 0){
// 				var size = users.length;
// 				var content = "";
// 				for (var i = 0; i < size; i++) {
// 					content += "<a class=\"qqconnect\" href=\"${ctx}/jsp_v2/websocket/websocketchatcommon.jsp?specialUser="+ users[i].userId +"&num=" + (i+1) +"\">语聊客服"+(i+1)+"<\/a><br><br>";
// 				}
// 				$("#recomindUsers").html(content);
// 			}
// 		}
// 	});
</script>
</html>
