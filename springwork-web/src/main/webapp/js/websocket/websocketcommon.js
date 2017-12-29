function getUrl() {     
	var index = contextPath.lastIndexOf("/");
	var urlPath = contextPath.substring(index, contextPath.length) + "/websocket";
	if (window.location.protocol == 'http:') {  
		url = 'ws://' + window.location.host + urlPath;  
	} else {  
		url = 'wss://' + window.location.host + urlPath;  
	}   
	return url;
} 

function disconnect(ws) {  
    if (ws != null) {  
        ws.close();  
        ws = null;  
    }  
}  

function replace_em(str){

//	str = str.replace(/\</g,'&lt;');
//
//	str = str.replace(/\>/g,'&gt;');
//
//	str = str.replace(/\n/g,'<br/>');

	str = str.replace(/\[em_([0-9]*)\]/g,'<img src="../../img/arclist/$1.gif" border="0" />');

	return str;

}

function connect(ws) { 
//	var url = getUrl();
//    //alert("url:"+url);  
//    if (!url) {  
//        return;  
//    }  
//    debugger;
//    ws = new WebSocket(url);  

    ws.onopen = function () {  
    	$("#ChatContent").append("<small>连接成功。。。</small><br>");
    };  
    ws.onmessage = function (event) {
    	if(typeof(event.data)=="string"){  
	    	var dataAll = event.data;
	    	var indexMsgType = dataAll.indexOf("|");
	    	var msgType = dataAll.substring(0,indexMsgType);
	    	if(msgType == "0000"){
	    		webid = dataAll.substring(indexMsgType+1,dataAll.length);
	    		$("#RandomContent").html("欢迎您,"+webid);
	    	}else{
	    		var data = dataAll.substring(indexMsgType+1,dataAll.length);
		    	var index = data.indexOf("|");
		    	var userId = data.substring(0,index);
		    	var msg = decodeURI(data.substring(index+1,data.length));
		    	var result = replace_em(msg);
		    	//if(document.getElementById("id")){
		    		//document.getElementById(userId).setAttribute('src', contextPath+'/img/msgget.gif');
			    	var number = $("#ChatContent").scrollTop();
		    		//var number = $("#ChatContent").height();
			    	number += 15;
			    	$("#ChatContent").scrollTop(number);
			    	$("#ChatContent").append("<strong>"+msgUser+" ：</strong>" + result + "<br>");
		    	//}
		    	
	    	}
	    }else{  
    	  var reader = new FileReader();  
    	  reader.onload = function(event){  
    	       if(event.target.readyState == FileReader.DONE){  
    	            var url = event.target.result;  
    	            if (imgName != msgUser){
    	            	$("#ChatContent").append("<p align=\"right\"><strong>"+imgName+" ：</strong>"+"<img src = "+url+" width='100px'/></p><br>");  
    	       		}else{
    	            	$("#ChatContent").append("<strong>"+imgName+" ：</strong>"+"<img src = "+url+" width='100px'/><br>");  
    	       		}
    	            if (fileImgSize != 0){
    	            	fileImgSize = fileImgSize - 1;
    	            }else{
    	            	imgName = msgUser;
    	            }
    	       }
    	   }  
	      reader.readAsDataURL(event.data);  
	    }  
    };  
    ws.onclose = function (event) {  
    	 if(ws != null){
    		 ws.close();  
    		 ws = null;
    	 }
    };  
} 
 