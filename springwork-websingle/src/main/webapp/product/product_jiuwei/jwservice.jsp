<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/head.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>产品申请</title>
<meta name="keywords" content="产品申请">
<meta name="description" content="提供在线产品申请会议">
<link href="${ctx}/css/suggest/style-2.2.3.css" rel="stylesheet">
<link href="${ctx}/css/suggest/about-2.0.6.css" rel="stylesheet">
<link href="${ctx}/css/page.css" type="text/css" rel="stylesheet"/> 
<script type="text/javascript" src="${ctx}/js/jquery/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.validate.min.js" ></script>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.3&key=608d75903d29ad471362f8c58c550daf"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>

<script>

    var _initNav = function(obj) {

        if(obj.status == 0 ){
            $('#nav-log').css('display','block');
        }else{
            if(obj.unpay != 0 ){
                $('.down-center dl dd i').eq(0).html(obj.unpay);
            }
            if(obj.needdeal != 0 ){
                $('.down-center dl dd i').eq(1).html(obj.needdeal);
            }
            if(obj.coupon !=0 ){
                $('.down-center dl dd i').eq(2).html(obj.coupon);
            }
            $('.user-infobox span').eq(0).html(obj.user);

            if(obj.message != 0){
                $('.user-infobox i').eq(1).html(obj.message);
            }
            $('#nav-user-info').css('display','block');

            if(obj.parentid == 0 ){
                $('#nav-ul li[data-name="sub"]').css('display','none');
                $('#nav-ul li[data-name="sub"]').html('<a href="javascript:void(0);" id="sw-account">切换子账号</a>');
            }

            if(obj.workorder != 0 ) {
                $("#workorder").html(obj.workorder);
            }

    }};


    $(function(){
        var url = window.location.href;
        $('#nav-log a:eq(0)').data('url',url);

    });
    function toIndex(){
    	window.location.href= "${ctx}/index.jsp";
    }
   
</script>
<!-- end 页头部分-->
<!-- start 内容部分-->
<div style="background-color: #75ceff;">
	<div style="display:inline;">
		<img class="imglogocm" style="margin-left: 160px;width: 5%;" alt="" src="${ctx}/img/logo.png" onclick="toIndex()">
	</div>
	<div style="display:inline;float:right">
		<img class="imglogoright" alt="" src="${ctx}/img/logoright.png">
	</div>
</div>
<div class="main-ct container">
	
    <form id="theform" method="post" action="${ctx}/product/createtask" onsubmit="return false">
	<div class="section section-suggest-type">
    <input name="tasktype" type="hidden" value="">
    <div class="section section-question-type">
    	<div class="title"><h2>选择服务类型</h2></div>
        <div class="radio-box">
        	 <ul class="clear">
                <li class="radio-item current">线下支持</li>
                <li class="radio-item">技术服务</li>
                <li class="radio-item">产品申请</li>
                <li class="radio-item">其他</li>
        	</ul>
        </div>
    </div>
    </div>
    <input name="type" type="hidden" value="1">
	<div class="section">
        <div class="title"><h2>问题描述</h2></div>
        <div class="fm-wrapper">
            <div class="fm-item"><input placeholder="需求" class="fm-text" name="title" id="title" type="text" style="color:#888;" value="标题" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>
        
        	<div class="fm-item"><textarea name="content" id="content" cols="" rows="" class="fm-textarea" placeholder="请尽量详细描述问题（大于10个汉字）" style="height:90px; color:#888;" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value == &#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;">请尽量详细描述问题（大于10个汉字）</textarea></div>
            
            <div class="fm-item"><input placeholder="期望价格" class="fm-text" name="money" id=""money"" type="text" style="color:#888;" value="期望价格" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>
            
            <div class="fm-item"><input placeholder="您的姓名" class="fm-text" name="name" id="name" type="text" style="color:#888;" value="您的姓名" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>

            <div class="fm-item"><input placeholder="您的邮箱地址" class="fm-text" name="email" id="email" type="text" style="color:#888;" value="您的邮箱地址" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>

            <div class="fm-item"><input placeholder="您的手机号码" class="fm-text" name="mobile" id="mobile" type="text" style="color:#888;" value="您的手机号码" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>
             <div class="fm-item"><input placeholder="您的地址" class="fm-text" name="address" id="address" type="text" style="color:#888;" value="您的地址" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>
            
            <div class="fm-item"><input name="" type="submit" class="btn btn-primary" value="提交"></div>
        </div>
    </div>
    </form>
    <div id='container' style="width:400px;height:400px;top: 40%;left: 60%;"></div>
</div>
<!-- end 内容部分-->
<script type="text/javascript">
	var map, geolocation;
	//加载地图，调用浏览器定位服务
	map = new AMap.Map('container', {
	    resizeEnable: true
	});
	map.plugin('AMap.Geolocation', function() {
	    geolocation = new AMap.Geolocation({
	        enableHighAccuracy: true,//是否使用高精度定位，默认:true
	        timeout: 10000,          //超过10秒后停止定位，默认：无穷大
	        buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
	        zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
	        buttonPosition:'RB'
	    });
	    map.addControl(geolocation);
	    geolocation.getCurrentPosition();
	    AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
	    AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
	});
	//解析定位结果
	function onComplete(data) {
	    var str=['定位成功'];
	    str.push('经度：' + data.position.getLng());
	    str.push('纬度：' + data.position.getLat());
	    if(data.accuracy){
	         str.push('精度：' + data.accuracy + ' 米');
	    }//如为IP精确定位结果则没有精度信息
	    str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
// 	    alert(data.formattedAddress);
		var addr = getAddress(data.position.getLng(),data.position.getLat());
	    $("#address").val(addr);
// 	    document.getElementById('tip').innerHTML = str.join('<br>');
	    
	}
	//解析定位错误信息
	function onError(data) {
// 	    document.getElementById('tip').innerHTML = '定位失败';
	}
	
	function getAddress(lng,lat){
		var addr ="";
		$.ajax({
			type : "post",
			async: false,
			url : "${ctx}/pub/getLocation",
			data : {lng : lng, lat:lat} ,
			dataType : "json",
			success : function(data) {
				console.log(data);
				addr = data;
			}
		});
		return addr;

	}

    $(".section-suggest-type li").click(function(){
        $(this).addClass("current").siblings().removeClass("current");
        $('input[name="tasktype"]').val($(this).text());
    });

    $(".section-question-type li").click(function(){
        $(this).addClass("current").siblings().removeClass("current");
        $('input[name="tasktype"]').val($(this).text());
    });

    // 显示提示文字
    $.fn.warning = function(str, timeout){

        if (str == undefined || str == "")
            return this.html("").removeClass("warning").hide();

        return this.each(function() {
            var o = $(this);

            if (o.attr("timeout")) {
                clearTimeout(o.attr("timeout"));
                o.removeAttr("timeout");
            }

            o.html(str).addClass("warning").show();
            if (timeout) {
                o.attr("timeout", setTimeout(function(){o.warning()}, timeout));
            }
        })
    };

    $.validator.methods.mobile = function(value, element, param) {
        var reg = /^1[3587]{1}[0-9]{9}$|^852[69]{1}[0-9]{7}$|^88609[0-9]{8}$|^853[6]{1}[0-9]{7}$/;
        return this.optional(element) || reg.test(value);
    };
    
    $.validator.methods.money = function(value, element, param) {
        var reg = /^[0-9]+$/;
        return this.optional(element) || reg.test(value);
    };

    $.validator.methods.cnname = function(value, element, param) {
        var reg = /^[\u4E00-\u9FA5()（）·]*$/;
        return this.optional(element) || reg.test(value);
    };

    //输入框不能为默认提示文字
    $.validator.methods.contentDefault = function(value, element, param) {
        return (value != '请尽量详细描述问题（大于10个汉字）');
    }

    $.validator.methods.nameDefault = function(value, element, param) {
        return (value != '您的姓名');
    }

    $.validator.methods.emailDefault = function(value, element, param) {
        return (value != '您的邮箱地址');
    }

    $.validator.methods.mobileDefault = function(value, element, param) {
        return (value != '您的手机号码');
    }
    
    $.validator.methods.moneyDefault = function(value, element, param) {
        return (value != '期望价格');
    }
    
    $.fn.serializeObject = function() {    
        var o = {};    
        var a = this.serializeArray();    
        $.each(a, function() {    
            if (o[this.name]) {    
                if (!o[this.name].push) {    
                    o[this.name] = [ o[this.name] ];    
                }    
                o[this.name].push(this.value || '');    
            } else {    
                o[this.name] = this.value || '';    
            }    
        });    
        return o;    
    }    

    $('#theform').validate({
        // onkeyup : false,
        // focusInvalid : false,
        // onfocusin : false,
        // onfocusout : false,

        errorPlacement: function(error, element) {
            error.css({'margin-left': '5px','color':'#ff0042'}).appendTo(element.parent("div"));
        },

        submitHandler: function(form) {
            var data = null;

            form = $(form);
            
            data = JSON.stringify(form.serializeObject());
            // form.disabled();
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                dataType : 'json',
                data : data,
                url : form.attr('action'),       
                success : function(ret) {
                   document.getElementById("resultdiv").style.display="";
                },
                error: function(res) {
                	document.getElementById("resultdivfail").style.display="";
                }
            });
        },

        rules : {
            name : {
                required : true,
                nameDefault : true,
                cnname : true,
                rangelength : [2, 6]
            },
            money : {
                required : true,
                moneyDefault : true,
                money : true
            },
            mobile : {
                required : true,
                mobileDefault : true,
                mobile : true
            },
            email : {
                required : true,
                emailDefault : true,
                email : true
            },
            content : {
                required: false,
                contentDefault : true,
                minlength : 10,
                maxlength : 500
            },
            seccode : {
                required: true,
                contentDefault : true,
                maxlength : 4
            }
        },

        messages : {
            name : {
                required : '请填写您的姓名',
                nameDefault : '请填写您的姓名',
                cnname : '姓名必须为汉字',
                rangelength : $.validator.format("长度{0}-{1}个汉字")
            },
            money : {
                required : '请填写金额',
                moneyDefault : '请填写金额',
                money : '金额格式不正确'
            },
            mobile : {
                required : '请填写联系手机',
                mobileDefault : '请填写联系手机',
                mobile : '手机号码格式不正确'
            },
            email : {
                required : '请输入邮箱地址',
                emailDefault : '请输入邮箱地址',
                email : '请输入有效的邮箱地址'
            },
            content : {
                required: '请输入详细的问题内容',
                contentDefault : '请输入详细的问题内容',
                minlength : '请输入至少10个字符的问题内容',
                maxlength : '问题内容最多500个字符'
            }
        }
    });
    
    function closewindows(){
    	document.getElementById("resultdivfail").style.display="none";
    }
</script>
<div id="resultdiv" style="display:none; POSITION:absolute; left:50%; top:50%; width:300px; height:100px; margin-left:-140px; margin-top:-12x; border:1px solid #888; background-color:#edf; text-align:center">您的申请已提交，谢谢您的支持！<br>
<br><br>
<a href="javascript:toIndex();">确定</a>
</div>
<div id="resultdivfail" style="display:none; POSITION:absolute; left:50%; top:50%; width:300px; height:100px; margin-left:-140px; margin-top:-12x; border:1px solid #888; background-color:#edf; text-align:center">提交申请失败，请稍后重试<br>
<br><br>
<a href="javascript:closewindows();">确定</a>
</div>
</body>
</html>