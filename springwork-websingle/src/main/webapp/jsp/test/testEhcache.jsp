<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/head.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/jquery/jquery-3.2.1.min.js" ></script>
<title>Test</title>
</head>
<body>
<a href="${ctx}/user/usertype?usertype=1001">test</a>

hello world
<script type="text/javascript">
$(document).ready(function(){
	var params = {
			userType : "1001"
	};
	console.log('userType:'+params);
	$.ajax({
		type : "post",
		url : "${ctx}/user/usertype",
		data : params ,
		dataType : "json",
		success : function(data) {
			console.log('userType:'+data);
		}
	});
});
</script>

</body>
</html>