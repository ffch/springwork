$(function() {
	var operaType = $("#loginId").attr("operaType");
	var loginCheck = {},
	flag = 1; //flag==0则验证不通过
	var regTel = /^0?(13[0-9]|15[012356789]|16[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/; //手机
	var regEmail = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,5}$/; //邮箱
	// 登录账户判断
	loginCheck.account = function(obj) {
		var mes = "";
		if (($.trim(obj.val()) != "") && !regTel.test($.trim(obj.val()))) {
			flag = 0;
			obj.siblings(".error_mess").html("请输入正确格式的手机号");
		} else {
			flag = 1;
			obj.siblings(".error_mess").html("&nbsp&nbsp");
		}
	}
	function requestAjax(type, url, data, callback) {
		$.ajax({
			type : type,
			url : url,
			data : data,
			dataType : "json",
			success : function(data) {
				callback(data);
			}
		});
	}
	function checkcount() {
		loginCheck.account($(".account"));
		if ($(".account").attr("operaType") != "login") { //登录不需要验证
			if (flag != 0 && ($.trim($(".account").val()) != "")) { //先判断是否为正确格式 0 ==不是 1==是
				var params = {
					loginId : $(".account").val(),
					loginIdType : "register"
				};
				requestAjax("post", passportUrl + "/pub/check/loginId", params, function(data) {
					if (data.status != 200) { //已注册
						flag = 0; //账号已注册 不鞥呢通过验证
						$(".account").siblings(".error_mess").html("您填写的账号已存在");
						$(".account").siblings(".last").removeClass("uhide");
					} else {
						flag = 1;
						$(".account").siblings(".error_mess").html("&nbsp");
						$(".account").siblings(".last").addClass("uhide");
					}
				})
			}
		}
	}
	
	
	$("#login-regis").validate({
		onkeyup : false,
		submitHandler : function(form) {
			if (flag != 0) { //验证通过 可开始调登录注册接口
				if (operaType == "login") { // 2.登录接口
					var params = {
						userName : $(".account").val(),
						userPwd : $(".passw").val(),
					};
					
					$.ajax({
						type : "post",
						url : passportUrl+"/login",
						dataType : "text",
						data : params,
						success : function(data) {
							location.assign(passportUrl + '/index.jsp');
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert(errorThrown);
						}
					});
				} else { // 开始调注册接口
					var	userName = $(".account").val();
					var password = $(".passw").val();
					checkcount();
					if (flag == 1) {
						$.ajax({
							type : "post",
							contentType: 'application/json',
							url : passportUrl+"/pub/register",
							dataType : "json",
							data : "{\"userName\":\""+userName+"\",\"password\":\""+password+"\"}",
							success : function(data) {
								location.assign(passportUrl + '/index.jsp'); // 注册成功后跳转企业选择页面
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(errorThrown);
							}
						});
						
//						requestAjax("post", passportUrl + "/pub/register", params, function(data) {
//							if (data.status == 200) { //注册成功
//								location.assign(passportUrl + '/index.jsp'); // 注册成功后跳转企业选择页面
//							} else { //注册失败
//								alert("注册失败");
//							}
//						})
					}

				}
			}
		},
		rules : {
			loginId : {
				required : true
			},
			password : {
				required : true,
				rangelength : [ 6, 20 ]
			}
		},
		messages : {
			loginId : {
				required : "请输入常用手机号"
			},
			password : {
				required : "请输入密码",
				rangelength : "密码长度不正确"
			}
		},
		errorPlacement : function(error, element) {
			element.siblings(".error_mess").html("&nbsp");
			error.appendTo(element.siblings(".error_mess"));
		}
	});
	
	$("#loginBtn,#regisBtn").on("click", function() {
		$("#login-regis").submit();
	})
	
	
})