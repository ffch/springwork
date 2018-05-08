<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/head.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>产品申请</title>
<meta name="keywords" content="产品申请">
<meta name="description" content="提供在线产品申请会议">
<link href="${ctx}/css/suggest/style-2.2.3.css" rel="stylesheet">
<link href="${ctx}/css/suggest/about-2.0.6.css" rel="stylesheet">
<link href="${ctx}/css/page.css" type="text/css" rel="stylesheet"/> 
<script type="text/javascript" src="${ctx}/js/jquery/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.validate.min.js" ></script>
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
	
    <form id="theform" method="post" action="${ctx}/suggest/createtask" onsubmit="return false">
	<div class="section section-suggest-type">
    <input name="type" type="hidden" value="建议">
    <div class="section section-question-type">
    	<div class="title"><h2>选择支持类型</h2></div>
        <div class="radio-box">
        	 <ul class="clear">
                <li class="radio-item current">基础服务</li>
                <li class="radio-item">技术支持</li>
                <li class="radio-item">产品申请</li>
                <li class="radio-item">会议申请</li>
                <li class="radio-item">其他</li>
        	</ul>
        </div>
    </div>
    <input name="tasktype" type="hidden" value="基础服务">
	<div class="section">
        <div class="title"><h2>问题描述</h2></div>
        <div class="fm-wrapper">
            <div class="fm-item"><input placeholder="标题" class="fm-text" name="title" id="title" type="text" style="color:#888;" value="标题" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>
        
        	<div class="fm-item"><textarea name="content" id="content" cols="" rows="" class="fm-textarea" placeholder="请尽量详细描述问题（大于10个汉字）" style="width:825px; height:90px; color:#888;" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value == &#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;">请尽量详细描述问题（大于10个汉字）</textarea></div>
           	<!-- <div class="fm-item clear">上传图片：  <input type="file" name="file" title="图片" id="file" _name="file" _optional="true"></div> -->
            <div class="fm-item"><input placeholder="您的姓名" class="fm-text" name="name" id="name" type="text" style="color:#888;" value="您的姓名" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>

            <div class="fm-item"><input placeholder="您的邮箱地址" class="fm-text" name="email" id="email" type="text" style="color:#888;" value="您的邮箱地址" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>

            <div class="fm-item"><input placeholder="您的手机号码" class="fm-text" name="mobile" id="mobile" type="text" style="color:#888;" value="您的手机号码" onfocus="if(value == defaultValue){value=&#39;&#39;;this.style.color=&#39;#888&#39;}" onblur="if(value==&#39;&#39;){value = defaultValue;this.style.color=&#39;#888&#39;}" onkeydown="this.style.color=&#39;#666&#39;"><span class="fm-need">*</span></div>
            <div class="fm-item"><input name="" type="submit" class="btn btn-primary" value="提交"></div>
        </div>
    </div>
    </form>
</div>
<!-- end 内容部分-->
<script type="text/javascript">
    $(".section-suggest-type li").click(function(){
        $(this).addClass("current").siblings().removeClass("current");
        $('input[name="type"]').val($(this).text());
    });

    $(".section-question-type li").click(function(){
        $(this).addClass("current").siblings().removeClass("current");
        $('input[name="questiontype"]').val($(this).text());
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
                required: true,
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
            },
            seccode : {
                required: '请输入验证码',
                contentDefault : '请输入验证码',
                maxlength : '验证码错误'
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