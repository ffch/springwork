<%@ page language="java" pageEncoding="UTF-8"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>授权</title>
</head>
<body>
	<h1>自定义 Approval</h1>
	<p>这是我自己的，怎么样？</p>
	<form id='confirmationForm' name='confirmationForm'
		action='/oauth/authorize' method='post'>
		<input name='user_oauth_approval' value='true' type='hidden' /><label><input
			name='authorize' value='Authorize' type='submit' /></label>
	</form>
	<form id='denialForm' name='denialForm' action='/oauth/authorize'
		method='post'>
		<input name='user_oauth_approval' value='false' type='hidden' /><label><input
			name='deny' value='Deny' type='submit' /></label>
	</form>
</body>
</html>