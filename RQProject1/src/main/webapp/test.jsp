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
	<c:out value="${fee}"></c:out>
	<table>
	         <tr>
	            <td><c:out value="${resv.rid}"></c:out></td>
	            <td><c:out value="${resv.plateNumber}"></c:out></td>
	            <td><c:out value="${resv.uid}"></c:out></td>
	         </tr>
      </table>
      
 <!--  
	<table>
	         <tr>
	            <td><c:out value="${plate.pid}"></c:out></td>
	            <td><c:out value="${plate.plateNumber}"></c:out></td>
	            <td><c:out value="${plate.uid}"></c:out></td>
	         </tr>
      </table>
      -->
<!-- 
	<table>
         <tr>
            <td>id</td>
            <td>account</td>
            <td>password</td>
            <td>name</td>
            <td>email</td>
            <td>phone</td>
         </tr>
	         <tr>
	            <td><c:out value="${user.uid}"></c:out></td>
	            <td><c:out value="${user.account}"></c:out></td>
	            <td><c:out value="${user.password}"></c:out></td>
	            <td><c:out value="${user.name}"></c:out></td>
	            <td><c:out value="${user.email}"></c:out></td>
	            <td><c:out value="${user.phone}"></c:out></td>
	         </tr>
      </table>
-->
</body>
</html>