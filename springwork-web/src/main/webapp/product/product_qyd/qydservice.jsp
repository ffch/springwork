<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/head.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>亲友贷</title>
<meta name="keywords" content="亲友贷">
<meta name="description" content="提供在线亲友借贷服务">
<link href="${ctx}/css/product/product.css" type="text/css" rel="stylesheet"/> 
<script type="text/javascript" src="${ctx}/js/jquery/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.validate.min.js" ></script>
</head>
<body>

<script>
    function toIndex(){
    	window.location.href= "${ctx}/index.jsp";
    }
    
    function submit(){
    	var name = $("#name").val();
    	var passwd = $("#passwd").val();
    	var checked = $("#checkbox").is(':checked');;
    	if(!checked){
    		alert("请阅读用户协议！");
    	}
    	$.ajax({
			type : "post",
			url : "${ctx}/product/openAccount",
			dataType : "json",
			data : {name : name,passwd : passwd} ,
			success : function(data) {
				if(data=="0000"){
					$("#error").html("开通成功");
				}else{
					$("#error").html(data);
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("#error").html(errorThrown);
			}
		});
    }
    
   
</script>

<div style="background-color: #75ceff;">
	<div style="display:inline;">
		<img class="imglogocm" style="margin-left: 160px;width: 5%;" alt="" src="${ctx}/img/logo.png" onclick="toIndex()">
	</div>
	<div style="display:inline;float:right">
		<img class="imglogoright" alt="" src="${ctx}/img/logoright.png">
	</div>
</div>
<div>
	<div style="margin-left: 200px;margin-top: 100px;">
		<span>姓名：</span>
		<input id="name" type="text" >
	</div>
	
	<div style="margin-left: 200px;margin-top: 50px;">
		<span>密码：</span>
		<input id="passwd" type="text" >
	</div>
	
	<div class="protocol" style="margin-left: 250px;margin-top: 50px;">
		<input id="checkbox" type="checkbox" >
		<a href = "#" style="">《请阅读用户协议》</a>
	</div>
	
	<input onclick="submit()" type="submit" style="margin-left: 250px;margin-top: 50px;width: 120px;height: 50px;font-size: 16px;" value="申请开通">
	<br>
	<div id="error" class="error"></div>
</div>
</body>
</html>