function closeDivWindows(id) {
//	document.getElementById(id).style.display = "none";
	$("#"+id).css('display','none'); 
}

function register(baseurl){
	window.location.href= baseurl + "/login.jsp";
}

function parseURL(url){
    var url = url.split("?")[1];
    var para = url.split("&");
    var len = para.length;
    var res = {};
    var arr = [];
    for(var i=0;i<len;i++){
        arr = para.split("=");
        res[arr[0]] = arr[1];
    }
    return res;
}
