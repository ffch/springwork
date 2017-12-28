<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/head.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>产品申请</title>
<meta name="keywords" content="申请列表">
<meta name="description" content="申请列表">
<link href="${ctx}/css/page.css" type="text/css" rel="stylesheet"/> 
<script type="text/javascript" src="${ctx}/js/jquery/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.validate.min.js" ></script>
<script type="text/javascript">
var passportUrl = '${ctx}';
	function toIndex(){
		window.location.href= passportUrl + "/index_v2.jsp";
	}
	
	function closeDetailWindows(){
    	document.getElementById("resultdivDetail").style.display="none";
    }
	
	function taskDetail(taskid,tasktype,content,name,email,mobile,title,curviewer,status){
		//alert(taskid+tasktype+content+name+email+mobile+title+curviewer+status);
		var contentReult = "<table border=\"1\" style=\"margin:20px;margin-left: 27px;border-collapse: collapse;border:1px solid #E0E0E0\">";
		var contentReultInline = contentReult;
		contentReult += "<tr><td width=\"120px\">任务id</td><td width=\"400px\">"+ taskid 
		+ "</td></tr><tr><td width=\"120px\">处理人</td><td width=\"400px\">" + curviewer
		+ "</td></tr><tr><td width=\"120px\">任务类型</td><td width=\"400px\">" + tasktype
		+ "</td></tr><tr><td width=\"120px\">任务状态</td><td width=\"400px\">" + status
		+ "</td></tr><tr><td width=\"120px\">内容</td><td width=\"400px\">"+ contentReultInline 
		+ "<tr><td width=\"120px\">姓名</td><td width=\"400px\">"+ name 
		+ "</td></tr><tr><td width=\"120px\">邮箱</td><td width=\"400px\">"+ email 
		+ "</td></tr><tr><td width=\"120px\">手机号</td><td width=\"400px\">"+ mobile 
		+ "</td></tr><tr><td width=\"120px\">内容</td><td width=\"400px\">"+ content 
		+ "</td></tr><tr><td width=\"120px\">标题</td><td width=\"400px\">" + title 
		+ "</td><tr></table></td></tr>";
		contentReult +="</table><a href=\"javascript:closeDetailWindows();\">确定</a>";
		$("#resultdivDetail").html(contentReult);
		document.getElementById("resultdivDetail").style.display="";
	}
	
	$(function() {
		$.ajax({
			type : "post",
			url : "${ctx}/suggest/applylist",
			data : {} ,
			dataType : "json",
			success : function(data) {
				var taskList = data;
				if(taskList != null && taskList.length != 0){
					var size = taskList.length;
					var content = "";
					for (var i = 0; i < size; i++) {
						var tmp = taskList[i];
						content += "<tr><td>"+ tmp.taskid + "</td><td>" + tmp.title + "</td><td>" + 
								tmp.curviewer + "</td><td>" + tmp.tasktype + "</td><td><a href='javascript:taskDetail(\""+tmp.taskid+"\",\""
								+ tmp.tasktype+"\",\"" + tmp.content+"\",\"" + tmp.name+"\",\""
								+ tmp.email+"\",\""+ tmp.mobile+"\",\""+ tmp.title+"\",\""+ tmp.curviewer+"\",\""
								+ tmp.status + "\");'>查看</a>&nbsp<a href=''>删除</a></td></tr>";
					}
					$("#applytable").append(content)
				}
			}
		});
	});
</script>
<style rel="stylesheet" type="text/css" media="all" />
td  
{  
    text-align:center;  
    background-color: #FFFFFF;
    height:40px;
}  
</style>
</head>
<body bgcolor="#F0F8FF">
<div style="background-color: #75ceff;">
	<div style="display:inline;">
		<img class="imglogocm" style="margin-left: 160px;width: 5%;" alt="" src="${ctx}/img/logo.png" onclick="toIndex()">
	</div>
	<div style="display:inline;float:right">
		<img class="imglogoright" alt="" src="${ctx}/img/logoright.png">
	</div>
</div>
<div style="background-color: #FFFFFF;">
<h2 style="margin: 22px;margin-left: 138px">
申请列表
</h2>
</div>
<table id="applytable" border="1" style="margin:20px;margin-left: 160px;border-collapse: collapse;border:1px solid #E0E0E0">
  <tr>
    <td width="100">任务id</td>
    <td width="600">标题</td>
    <td width="100">当前处理人</td>
    <td width="100">任务类型</td>
    <td width="100">操作</td>
  </tr>
</table>
<div id="resultdivDetail" style="display:none; POSITION:absolute; left:50%; top:10%; width:600px; height:500px; margin-left:-232px; margin-top:-12x; border:1px solid #888; background-color:#edf; text-align:center">详细信息<br>
<br><br>
<a href="javascript:closewindows();">确定</a>
</div>
</body>
</html>