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
<link href="${ctx}/css/account/account.css" type="text/css" rel="stylesheet" />

<script type="text/javascript"
	src="${ctx}/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/product/product.js"></script>
<script type="text/javascript"
	src="${ctx}/js/account/account.js"></script>	
<script type="text/javascript"
	src="${ctx}/js/common.js"></script>
<title>模板网站</title>
<script type="text/javascript">
var passportUrl = '${ctx}';	
</script>
</head>
<body>
	<a name="indexPage"></a>
	<div>
		<script type="text/javascript" color="0,0,255" opacity="0.7"
			zindex="-2" count="99" src="${ctx}/js/canvas/canvas-nest.min.js">
</script>
	</div>
	<div>
		<div id="sidebar">
			<ul style="margin-top: -129px;">
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
			<a class="qqconnect" href="${ctx}/jsp_v2/suggestion/watinglist.jsp">我的待办</a><br> <br>
			<a class="qqconnect" href="${ctx}/jsp_v2/suggestion/managelist.jsp">我的审批</a><br> <br>
		</div>
		<div class="nav-content" id="asset-content">
			<div class="nav-con-close">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</div>
			<div style="margin-left: 35%;">我的资产</div>
			<div id="assetInfo" class="assetInfo">您还没开通钱包功能</div>
			<div id="config"  class="assetconfig">
				<a id="openAccount" href="#" onclick="openAccount()">开通</a>
			</div>
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
			<br> <br> <a class="qqconnect" target="_blank"
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
		<div
			style="background: #007979; position: fixed; width: 100%; height: 60px; top: 0; z-index: 99999;">
			<div style="display: inline">
				<img class="imglogo" alt="" src="${ctx}/img/logo.png"> <span
					class="textlogo">IT服务站</span>
			</div>
			<div id="lr_systembtn" class="lr_systembtn">
				<a href="#" class="experience-btn bs-btn-red" id="loginRemide"
					onmouseover="this.style.textDecoration='none';"><span>为您提供</span></a>
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
			<div style="display: inline; float: right; margin-right: 50px;"
				id="prod">
				<ul class="baidu-header__navbar">
					<li class="prodli"><a class="prodlitext" href="#indexPage">首页</a></li>
					<li class="prodli"><a class="prodlitext" href="#solutionPage">解决方案</a></li>
					<li class="prodli"><a class="prodlitext" href="#superiority">优势</a></li>
					<li class="prodli"><a class="prodlitext" href="#safety">安全保障</a></li>
					<li class="prodli"><a class="prodlitext" href="#choice_ikcrm">我们的客户</a></li>
				</ul>
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
						onclick="register(passportUrl)">
				</div>
				<div>
					<img class="bgfront" alt="" src="${ctx}/img/index1001.png">
				</div>
			</div>
			<div>
				
				<div id= "solutionPage" class="item_bg_02 bs_item-bg_02">
					<div class="bs-s1-bg">
						<span class="textsolution">就位</span>
						<ul >
							<li>这是一个it服务站。</li>
							<li>这是一个全方位的服务站。</li>
							<li>您提需求，我来解决。</li>
							<li>您开价格，我来动手。</li>
							<li>您可以主动联系我们，也可以留下您的足迹。</li>
							<li></li>
							<li></li>
						</ul>
					</div>
					<div class="bs-s2-bg">
						<span class="textsolution">亲友贷</span>
						<ul >
							<li>服务大众，解决您的资金问题。</li>
							<li>不低于余额宝的收益。</li>
							<li>您的钱，只会给有需要的亲朋。</li>
							<li>我们不是第三方支付，我们只是大自然的搬运工。</li>
							<li>留下您的需求，我们会为你们牵线搭桥。</li>
							<li></li>
							<li></li>
						</ul>
					</div>
					<br/><br/>
					<div class="bs-s2-bg_btn">
						<a href="javascript:;" onmouseover="this.style.textDecoration='none';" class="s1 experience-btn_2 bs-btn-blue" onclick="gotoJiuwei()">立即体验</a>
					</div>
					<div class="bs-s2-bg_btn">
						<a href="javascript:;" onmouseover="this.style.textDecoration='none';" class="s2 experience-btn_2 bs-btn-green" onclick="gotoQinyou()">立即体验</a>
					</div>
				</div>
		</div>
		</div>
		
	</div>
	<div id="rechargeAccount" class="rechargeAccount">
		<div id="rechargeAccountContent" style="margin-top: 20px;">
			<span style="font-size: 20px;font-weight:bold;">请充值</span>
			<div style="margin-top: 25px;">
				<span>金额：</span>
				<input id="rechAmt" type="text" >
			</div>
			
			<div style="margin-top: 35px;margin-bottom: 20px;">
				<span>密码：</span>
				<input id="rechPwd" type="text" >
			</div>
			<div style="margin-bottom: 20px;">
				<span id="rechError" >&nbsp;</span>
			</div>
		</div>
		<br>
		<input type="button" class="accountbtn" onclick="rechargeAccount()" value="充值"/>
		<input type="button" class="accountbtn" onclick="closeDivWindows('rechargeAccount')" value="取消"/>
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
		dataType : "json",
		success : function(data) {
			console.log(data);
			var userId = data.userName;
			if(userId != null && userId != ""){
				loginRemide = "欢迎您，" + userId;
				loginflag = true;
				typeUser = data.userType;
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
					
					queryAccount();
				}
				
				
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
// 				alert(errorThrown);
		}
	});
    console.log(loginflag);
    console.log(loginRemide);
    console.log(loginUrl);
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
	
	$.ajax({
		type : "post",
		url : "${ctx}/pub/getSpecialUser",
		data : {userType : "2001"} ,
		dataType : "json",
		success : function(data) {
			var users = data;
			if(users != null && users.length != 0){
				var size = users.length;
				var content = "";
				for (var i = 0; i < size; i++) {
					content += "<a class=\"qqconnect\" href=\"${ctx}/jsp_v2/websocket/websocketchatcommon.jsp?specialUser="+ users[i].userName +"&num=" + (i+1) +"\">语聊客服"+users[i].userName+"<\/a><br><br>";
				}
				$("#recomindUsers").html(content);
			}
		}
	});
</script>
</html>
