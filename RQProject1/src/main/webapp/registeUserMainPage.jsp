<!DOCTYPE html>
<html lang="zh-CN">
  <head>
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
				  <li role="presentation " class="active"><a>Home</a></li>
				  <li role="presentation "><a class="navigation" href="aaa/reservationList">Reservation</a></li>
				  <li role="presentation "><a class="navigation" href="aaa/aab?username=123&password=123">Contract</a></li>
				  <li role="presentation "><a class="navigation" href="aaa/aab?username=123&password=123">Bill</a></li>
				  <li role="presentation "><a class="navigation" href="aaa/Operation">Operation</a></li>
				</ul>
			</div>
			<div class="col-md-1">
				<ul class="nav nav-pills">
					<li role="presentation" class="dropdown">
						 <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
						 	sign out
						 </a>
					</li>
				</ul>
				
			</div>
		</div>
		<div class="row" style="height: 280px; margin: 30px 0 0 0;">
			<div class="col-md-1" >
			
			</div>
			<div class="col-md-5" style="height: 100%;">
				<div class="panel panel-default" style="height: 100%;">
					<div class="panel-heading">user information</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-9">
								<table class="table table-hover">
									<tr>
										<td>Name</td>
										<td>Max</td>
									</tr>
									<tr>
										<td>age</td>
										<td>25</td>
									</tr>
									<tr>
										<td>Email</td>
										<td>Max@gmail.com</td>
									</tr>
									<tr>
										<td>Phone</td>
										<td>5092181234</td>
									</tr>
								</table>
							</div>
							<div class="col-md-3">
								<a  class="thumbnail">
							      <img src="img/download.svg" alt="headSculpture">
							    </a>
							</div>
						</div>
						<div class="row text-right">
							<button type="button" class="btn btn-link">More</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-5" style="height: 100%;">
				<div class="panel panel-default" style="height: 100%;">
					<div class="panel-heading">resevation</div>
					<div class="panel-body">
						<div class="row">
							<table class="table table-hover">
								<tr>
									<td>name</td>
									<td>plate number</td>
									<td>date</td>
								</tr>
								<tr>
									<td>max</td>
									<td>123456</td>
									<td>3/13</td>
								</tr>
								<tr>
									<td>max</td>
									<td>123456</td>
									<td>3/13</td>
								</tr>
								<tr>
									<td>max</td>
									<td>123456</td>
									<td>3/13</td>
								</tr>
							</table>
						</div>
						<div class="row text-right">
							<button type="button" class="btn btn-link">More</button>
						</div>
					</div>
					
				</div>
			</div>
			<div class="col-md-1" >
			
			</div>
		</div>
		<div class="row" style="height: 280px; margin: 30px 0 0 0;">
			<div class="col-md-1" >
			
			</div>
			<div class="col-md-5" style="height: 100%;">
				<div class="panel panel-default" style="height: 100%;">
					<div class="panel-heading">Contract</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-9">
								<table class="table table-hover">
									<tr>
										<td>Name</td>
										<td>Max</td>
									</tr>
									<tr>
										<td>age</td>
										<td>25</td>
									</tr>
									<tr>
										<td>Email</td>
										<td>Max@gmail.com</td>
									</tr>
									<tr>
										<td>Phone</td>
										<td>5092181234</td>
									</tr>
								</table>
							</div>
							<div class="col-md-3">
								<a  class="thumbnail">
							      <img src="img/download.svg" alt="headSculpture">
							    </a>
							</div>
						</div>
						<div class="row text-right">
							<button type="button" class="btn btn-link">More</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-5" style="height: 100%;">
				<div class="panel panel-default" style="height: 100%;">
					<div class="panel-heading">Bill</div>
					<div class="panel-body">
						<div class="row">
							<table class="table table-hover">
								<tr>
									<td>name</td>
									<td>plate number</td>
									<td>date</td>
								</tr>
								<tr>
									<td>max</td>
									<td>123456</td>
									<td>3/13</td>
								</tr>
								<tr>
									<td>max</td>
									<td>123456</td>
									<td>3/13</td>
								</tr>
								<tr>
									<td>max</td>
									<td>123456</td>
									<td>3/13</td>
								</tr>
							</table>
						</div>
						<div class="row text-right">
							<button type="button" class="btn btn-link">More</button>
						</div>
					</div>
					
				</div>
			</div>
			<div class="col-md-1" >
			
			</div>
		</div>
	</div>
    
  </body>
</html>