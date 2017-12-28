<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/head.jsp"%>
<%-- <%@ page import="com.cff.domain.User" %> --%>
<%-- <% --%>
// 	User userInfo = (User)request.getSession().getAttribute("userInfo");
<%-- %> --%>
<!-- <script src="http://www.jq22.com/js/jq.js"></script> -->
<script type="text/javascript" src="${ctx}/js/jquery/jquery-3.2.1.min.js" ></script>
<script src="${ctx}/js/websocket/websocket.js"></script>
<script src="${ctx}/js/jquery/ajaxfileupload.js"></script>
<script src="${ctx}/js/jquery/jquery-browser.js"></script>
<script src="${ctx}/js/jquery/jquery.qqFace.js"></script>
<head>
<title>聊天室</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="content-script-type" content="text/javascript">
<meta http-equiv="content-style-type" content="text/css">
<style rel="stylesheet" type="text/css" media="all" />
body { text-align:left; margin:0; font:normal 12px Verdana, Arial;
background:#FFEEFF } form { margin:0; font:normal 12px Verdana,
Arial; } table,input { font:normal 12px Verdana, Arial; }
a:link,a:visited{ text-decoration:none; color:#333333; } a:hover{
text-decoration:none; color:#FF6600 } #main { width:400px;
position:absolute; left:600px; top:100px; background:#EFEFFF;
text-align:left; filter:Alpha(opacity=90) } #ChatHead {
text-align:right; padding:3px; border:1px solid #003399;
background:#DCDCFF; font-size:20px; color:#3366FF; cursor:move; }
#ChatHead a:link,#ChatHead a:visited, { font-size:14px;
font-weight:bold; padding:0 3px } #ChatBody { border:1px solid
#003399; border-top:none; padding:2px; } #ChatContent {
height:200px; padding:6px; overflow-y:scroll; word-break: break-all
}#ChatBtn { border-top:1px solid #003399; padding:2px }
</style>
<script language="javascript" type="text/javascript">
var ws = null;
var msgUser=null;
var muserId = null;
<%-- var nickName =<%=userInfo.getLoginId()%>; --%>
var contextPath = '${ctx}';
var imgName = null;
var fileImgSize = 0;
function ChatSetUser(user,userId) {
	if(muserId != null && muserId != userId){
		$("#ChatContent").html("");
	}
	msgUser = user;
	muserId = userId;
	imgName = msgUser;
	//alert("owerId:" + nickName + "-----msgUser:" + msgUser);
	//alert("userId:" + nickName + "-----userId:" + muserId);
	if (msgUser == nickName) {
		return;
	}
	if (msgUser == "") {
		return;
	}
	ChatNew();
}
function gs(d) {
	var t = document.getElementById(d);
	if (t) {
		return t.style;
	} else {
		return null;
	}
}
function gs2(d, a) {
	if (d.currentStyle) {
		var curVal = d.currentStyle[a]
	} else {
		var curVal = document.defaultView
				.getComputedStyle(d, null)[a]
	}
	return curVal;
}
function ChatHidden() {
	gs("ChatBody").display = "none";
}
function ChatShow() {
	gs("ChatBody").display = "";
}
function ChatClose() {
	gs("main").display = "none";
	//disconnect(ws);
}
function ChatNew() {
	gs("main").display = "";
	$("#ChatUsers").html(msgUser);
	$('.emotion').qqFace({

		id : 'facebox', 

		assign:'saytext', 

		path: contextPath+'/img/arclist/'	//表情存放的路径

	});
}
function ChatClear(obj) {
	$("#ChatContent").html("");
}

function ChatRead() {
	document.getElementById(msgUser).setAttribute('src', contextPath+'/img/users.png');
}

function ChatSend(obj) {
	var o = obj.ChatValue;
	var msg = replace_em(o.value);
	if (o.value.length > 0) {
		$("#ChatContent").append(
				"<p align=\"right\"><strong>" + nickName + "(我) ：</strong>" + msg
						+ "</p>");
		var number = $("#ChatContent").scrollTop();
    	number += 16;
    	$("#ChatContent").scrollTop(number);
		$.ajax({
			url : "${ctx}/webSocket/send",
			data : {
				"userId" :muserId,
				"msg" : encodeURI(o.value)
			},
			dataType : "text",
			timeout : 5000,
			error : function(XMLHttpRequest,
					textStatus, errorThrown) {
				$("#ChatContent").append(
						"消息发送失败，请检查网络！"
								+ o.value
								+ "<br/>");
			},
			success : function(data, textStatus) {
				if(data == "100"){
					$("#ChatContent").append(
    						"请重新进入语聊！"
    								+ "<br/>");
				}
			}
		});
		o.value = '';
	}
	
	var img = obj.ChatFile;
	if (img.value.length > 0){
		$("#ChatContent").append(
				"<p align=\"right\"><strong>" + nickName + "(我) ：</strong>" + img.value
						+ "</p><br/>");

		imgName = nickName+'（我）';
		fileImgSize = img.files.length;
		//alert(fileImgSize);
		$.ajaxFileUpload({
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
			url:'${ctx}/webSocket/fileUpload?userId='+muserId,
			secureuri:false,                       //是否启用安全提交,默认为false 
			fileElementId:'ChatFile',           //文件选择框的id属性
			dataType:'text',                       //服务器返回的格式,可以是json或xml等
			success:function(data, status){        //服务器响应成功时的处理函数
				//$("#ChatContent").append("<p align=\"right\">" + data + "</p><br/>");
			},
			error:function(data, status, e){ //服务器响应失败时的处理函数
				$("#ChatContent").append('<p align=\"right\">图片上传失败，请重试！！</p><br/>');
				imgName = msgUser;
			}
		});
	}
}
	if (document.getElementById) {
		(function() {
			if (window.opera) {
				document.write("<input type='hidden' id='Q' value=' '>");
			}

			var n = 500;
			var dragok = false;
			var y, x, d, dy, dx;

			function move(e) {
				if (!e)
					e = window.event;
				if (dragok) {
					d.style.left = dx + e.clientX - x + "px";
					d.style.top = dy + e.clientY - y + "px";
					return false;
				}
			}

			function down(e) {
				if (!e)
					e = window.event;
				var temp = (typeof e.target != "undefined") ? e.target
						: e.srcElement;
				if (temp.tagName != "HTML" | "BODY"
						&& temp.className != "dragclass") {
					temp = (typeof temp.parentNode != "undefined") ? temp.parentNode
							: temp.parentElement;
				}
				if ('TR' == temp.tagName) {
					temp = (typeof temp.parentNode != "undefined") ? temp.parentNode
							: temp.parentElement;
					temp = (typeof temp.parentNode != "undefined") ? temp.parentNode
							: temp.parentElement;
					temp = (typeof temp.parentNode != "undefined") ? temp.parentNode
							: temp.parentElement;
				}

				if (temp.className == "dragclass") {
					if (window.opera) {
						document.getElementById("Q").focus();
					}
					dragok = true;
					temp.style.zIndex = n++;
					d = temp;
					dx = parseInt(gs2(temp, "left")) | 0;
					dy = parseInt(gs2(temp, "top")) | 0;
					x = e.clientX;
					y = e.clientY;
					document.onmousemove = move;
					return false;
				}
			}

			function up() {
				dragok = false;
				document.onmousemove = null;
			}

			document.onmousedown = down;
			document.onmouseup = up;

		})();
	}

	$(function() {
		if (ws == null) {
			connect(ws);
		}
		ChatClose();
// 		ChatSetUser("human","16888888888");
	})
</script>
</head>

<body>
	
	<div id="main" class="dragclass" onclick="ChatRead()" style="left: 400px; top: 200px;">
		<div id="ChatUsers" style="width:40px; padding:3px; font-size:15px;float:left; display:inline"></div>
		<div id="ChatHead">
			<a href="#" onclick="ChatHidden();">-</a> <a href="#"
				onclick="ChatShow();">+</a> <a href="#" onclick="ChatClose();">x</a>
		</div>
		<div id="ChatBody">
			<div id="ChatContent"></div>
			<div id="ChatBtn">
				<form action="" name="chat" method="post">
					<textarea name="ChatValue" id="saytext" rows="3" style="width: 350px"></textarea>
					<input name="Submit" type="button" value="发送"
						onclick="ChatSend(this.form);" />
					<input name="ClearMsg" type="button" value="清空记录"
						onclick="ChatClear(this.form);" />
					<input type="button" class="emotion" value="表情">
					<input id="ChatFile" type="file" name="myfiles"  multiple>   
				</form>
			</div>
		</div>
	</div>
	<div align="left">
		<p id=RandomContent>欢迎您，15607110725</p>
		<p id=content></p>
	</div>
	
</body>
</html>