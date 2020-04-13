<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	%>
	<base href="<%=basePath%>"></base>
    <title>Bootstrap 101 Template</title>
    <!-- Bootstrap -->
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <style>
    	html,body { height: 100%; padding: 0 0 0; margin: 0; background-color:#dff0d8;}
    	.outer{height:100%; position:relative;}
    	.header{background:#292929;}
    	.main{height:100%;}
    	.side{height :100%; background-color:#981E32; position: relative;}
    	.content{height :100%;  position: relative; padding:0 0 0;}
    	.navigation{color:white;}
    	.nav-pills>li.active>a, .nav-pills>li.active>a:focus, .nav-pills>li.active>a:hover{
    		color: #fff;
   			background-color: #555;
    	}
    </style>
    <script type="text/javascript">
    	function goToMakeReservation(){
    		$(location).attr('href', 'reservation/makeReservation');
    	}
    	function sighout(){
			$(location).attr('href', 'user/sighout');
			
		}
    </script>
  </head>
  <body>
    

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="jquery/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<div class="container-fluid outer" >
		<div class="row header	" >
			<div class="col-md-2" >
				<img src="img/wsu-home-logo.svg" class="img-responsive" alt="Responsive image">
			</div>
			<div class="col-md-9" style="padding: 10px 0 0 0;">
				<ul class="nav nav-pills">
				  <li role="presentation " ><a class="navigation" href="user/login?username=123&password=123">Home</a></li>
				  <li role="presentation " class="active"><a>Reservation</a></li>
				  <li role="presentation "><a class="navigation" href="aaa/aab?username=123&password=123">Bill</a></li>
				</ul>
			</div>
			<div class="col-md-1">
				<ul class="nav nav-pills">
					<li role="presentation" class="dropdown">
						 <a class="dropdown-toggle" data-toggle="dropdown" onClick="sighout()" role="button" aria-haspopup="true" aria-expanded="false">
						 	sign out
						 </a>
					</li>
				</ul>
				
			</div>
		</div>
		<div class="row">
			<div class="col-md-1" >
			</div>
			<div class="col-md-10" >
			<div class="panel panel-default" style="height: 100%;">
				<div class="panel-heading">Reservation List</div>
				<div class="panel-body">
					<div class="row text-right" style="margin:15px 0 0 0;">
						<button type="button" class="btn btn-default" onClick="goToMakeReservation();">Make a Reservation</button>
					</div>
					<div class="row" style="margin:15px 0 0 0; ">
						<table class="table table-hover">
							<tr>
								<td>Reservation Number</td>
								<td>Start From</td>
								<td>End To</td>
								<td>Plate Number</td>
								<td>Park Number</td>
							</tr>
							<c:forEach var="item" items="${reservationList}">
								<tr>
									<td><c:out value="${item.rid}"></c:out></td>
									<td><c:out value="${item.rStartDate}"></c:out></td>
									<td><c:out value="${item.rEndDate}"></c:out></td>
									<td><c:out value="${item.plateNumber}"></c:out></td>
									<td><c:out value="${item.parkNumber}"></c:out></td>
								</tr>
							</c:forEach>
						</table>		
					</div>
					<form type="post" action="reservation/cancel">
						<input list="numberList" class="form-control" id="reservationNumber" name="reservationNumber" placeholder="reservationNumber to cancel"  type="text">
						<div class="col-md-2"><button type="submit" class="btn btn-default">Submit</button></div>
					</form>
				</div>
			</div>
			</div>
			<div class="col-md-1" >
			</div>
		</div>
	</div>
    
  </body>
</html>