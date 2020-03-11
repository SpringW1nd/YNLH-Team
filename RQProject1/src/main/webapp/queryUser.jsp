<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryUsers</title>
</head>
<body>
	<table>
         <tr>
            <td>id</td>
            <td>name</td>
            <td>type</td>
         </tr>
         <c:forEach items="${userList}" var="user">
	         <tr>
	            <td><c:out value="${user.uid}"></c:out></td>
	            <td><c:out value="${user.name}"></c:out></td>
	            <td><c:out value="${user.type}"></c:out></td>
	         </tr>
         </c:forEach>
      </table>
</body>
</html>