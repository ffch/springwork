<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/head.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%
	String userName = (String)request.getSession().getAttribute("userName");
	Boolean flag = false;
	String loginContent = "已有账号？登陆";
	String loginId = "";
	if(userName != null || ! "".equals(userName)){
		flag = true;
		loginContent = "欢迎您，" + userName;
		loginId = userName;
	}else{
		loginId = "";
	}
	
%> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<link href="${ctx}/css/page.css" type="text/css" rel="stylesheet"/> 
<link href="${ctx}/css/menu.css" type="text/css" rel="stylesheet"/> 
<script type="text/javascript" src="${ctx}/js/jquery/jquery-3.2.1.min.js" ></script>
<title>个人管理</title>
</head>
<body>
<div style="text-align: center;">
	<div style="display:inline;">
		<img class="imglogocm" alt="" src="${ctx}/img/logo.png" onclick="toIndex()">
	</div>
	<div style="display:inline;float:right">
		<img class="imglogoright" alt="" src="${ctx}/img/logoright.png">
	</div>
	
	<menu class="menu">
		<div class="w1170">
			<ul>
		  		<li>
		  			<a href="${ctx}/index_v2.jsp">首页</a>
		  		</li>   
				<li class="on">  
	                <a href="#">系统管理</a>
	            </li>
	        </ul>
        </div>
	</menu>
	
	
<section class="content" id="content">
<aside class="left_con">
<!--背景层--> 
<section class="content1" id="content1">
   <aside class="left_con">
    	<div class="left_con_tit borderb">系统管理</div>
        <ul>
        	<li><a href="#">修改密码</a></li>      	
        </ul>
</aside> 
   </section>  
	</aside>
	<form id="UserPwdForm" action="${ctx}/user/modifypasswd" method="post">
	<input type="hidden" name="loginId" value="<%=loginId%>"> 
	
    <section class="boxRight" id="boxRight">
    	<div class="boxRight_tit f1 borderb">修改密码</div>
        <article class="boxRightCon">
        	<div class="chagepassword">
            	<ul>
                	<li><span class="name f2">原密码</span><span class="fl"><input type="password" id="pswd" name="password" oncopy="return false" oncut="return false" onpaste="return false" tabindex="1" maxlength="20"></span><span class="fl color9">请输入原密码</span></li>
                    <li><span class="name f2">新密码</span><span class="fl"><input type="password" id="passwordnew" name="passwordnew" oncopy="return false" oncut="return false" onpaste="return false" tabindex="2" maxlength="20"></span><span class="fl color9">8~20位长度</span></li>
                    <li><span class="name f2">确认密码</span><span class="fl"><input type="password" id="passwordconfirm" name="passwordconfirm" oncopy="return false" oncut="return false" onpaste="return false" tabindex="3" maxlength="20"></span><span class="fl color9">请输入新密码</span></li>
                </ul>
				<div class="error" id="errors_div" style="display:none;"><span id="errors_msg">错误提示！</span></div>
                <div class="changepassword_btn vip_btn"><span class="btn fabu_btn"><a class="UserPwdSubbtn" href="javascript:void(0);" id="UserPwdSubForm" >确定</a></span></div>
            </div>
        </article>
    </section>
    </form>
</section>
</div>
</body>
<script type="text/javascript">
$(function() {
	
	function checkPwd(passwd){
		if(passwd.length > 20 || passwd.length < 6){
			alert(passwd);
			return false;
		}
		return true;
	}
	
	$("#UserPwdSubForm").on("click", function() {
		var pswd = $("#pswd").val();
		var passwordnew = $("#passwordnew").val();
		var passwordconfirm = $("#passwordconfirm").val();
		if(!checkPwd(pswd))return;
		if(!checkPwd(passwordnew))return;
		if(!checkPwd(passwordconfirm))return;
		if(passwordnew != passwordconfirm){
			return;
		}
		$("#UserPwdForm").submit();
	})
	
	var resType = "<%=request.getParameter("type")%>"; 
	if(resType != null && resType!="" && resType!="null"){
		if(resType=="100"){
			$("#errors_div").css('display','block'); 
			$("#errors_msg").text("密码错误");
		}
		if(resType=="101"){
			$("#errors_div").css('display','block'); 
			$("#errors_msg").text("两次密码不一致");
		}
		if(resType=="102"){
			$("#errors_div").css('display','block'); 
			$("#errors_msg").text("修改密码失败");
		}
		if(resType=="200"){
			$("#errors_div").css('display','block'); 
			$("#errors_msg").text("修改成功");
		}
	}
})
</script>
</html>