function queryAccount() {
	$.ajax({
		type : "post",
		url : appUrl + "/product/queryAccount",
		data : {},
		dataType : "json",
		success : function(data) {
			var accountList = data;
			if (accountList == null) {
				$('#openAccount').removeAttr('href');// 去掉a标签中的href属性
				$('#openAccount').removeAttr('onclick');// 去掉a标签中的onclick事件
			}
			if (accountList.length != 0) {
				var size = accountList.length;
				var content = "<table border=\"1\">";

				for (var i = 0; i < size; i++) {
					var account = accountList[i];
					content += "<caption>" + account.accName + "</caption>";
					content += "<tr><th>余额：</th><th>" + account.bal
							+ "</th></tr>";
					content += "<tr><th>冻结金额：</th><th>" + account.frzBal
							+ "</th></tr>";
					content += "<tr><th>借出金额：</th><th>" + account.loanBal
							+ "</th></tr>";
				}
				$('#assetInfo').html(content);
				$('#config').children('a').remove();
				$('#config').append("<a href=\"#\" onclick=\"rechargeAccountWindow()\">充值</a>");
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

function openAccount(){
	window.location.href= appUrl + "/product/product_qyd/qydservice.jsp";
}

function rechargeAccountWindow(){
	$("#rechargeAccount").css('display','block'); 
}

function rechargeAccount(){
	var amt = $("#rechAmt").val();
	var pwd = $("#rechPwd").val();
	
	if(amt == null || amt == "" || pwd == null || pwd == ""){
		alert("金额及密码不能为空");
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/product/rechargeAccount",
		data : {amt : amt,pwd : pwd},
		dataType : "json",
		success : function(data) {
			$("#rechError").html("<font color=\"red\">"+data+"</font>");	
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#rechError").html("<font color=\"red\">"+ errorThrown+"</font>");
		}
	});
}