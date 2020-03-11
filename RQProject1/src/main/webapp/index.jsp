<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h2>registerUser</h2>
	<form action="user/registerUser" method="get">
		<input type="text" name="name"/><br/>
		<input type="password" name="password"/><br/>
		<input type="submit" value="register"/>
	</form>

	<h2>insertUser</h2>>
	<form action="user/insertUser" method="get">
		<input type="text" name="uid"/><br/>
		<input type="text" name="name"/><br/>
		<input type="text" name="type"/><br/>
		<input type="submit" value="add"/>
	</form>
</body>
</html>
