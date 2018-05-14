function closeDivWindows(id) {
//	document.getElementById(id).style.display = "none";
	$("#"+id).css('display','none'); 
}

function register(baseurl){
	window.location.href= baseurl + "/login.jsp";
}
