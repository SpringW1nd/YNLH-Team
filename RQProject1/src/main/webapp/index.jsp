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
    	html,body { height: 100%; padding: 0 0 0; margin: 0; }
    	.outer{height:100%; position:relative;}
    	.header{background:#292929;}
    	.main{height:100%;}
    	.side{height :100%; background-color:#981E32; position: relative;}
    	.content{height :100%;  position: relative; padding:0 0 0;}
    </style>
    <script>
    
    	function goToRegisteration(){
    		$(location).attr('href', 'user/goTORegisteration');
    		
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
			<div class="col-md-9" >
				
			</div>
			<div class="col-md-1">
				<ul class="nav nav-pills">
					<li role="presentation" class="dropdown">
						 <a class="dropdown-toggle" data-toggle="dropdown" onClick="goToRegisteration();" role="button" aria-haspopup="true" aria-expanded="false">
						 	sigh up
						 </a>
					</li>
				</ul>
				
			</div>
		</div>
		<div class="row main" >
			<div class="col-md-4 side" >
				<form method="post" action="user/login">
					<div class="bs-example pager center-block" data-example-id="basic-forms" style="color:white;padding: 30px 10px 30px 15px;border-radius: 11px;background-color: #292929;margin: 80px 10px 10px 10px;">
						<div class="row text-left">
							<h4>Registed User:</h4>
						</div>
						<div class="form-group text-left">
						    <label for="username">Username</label>
						    <input type="text" class="form-control" id="username" name="username" placeholder="usename">
						</div>
						<div class="form-group text-left">
						    <label for="password">Password</label>
						    <input type="password" class="form-control" id="password" name="password" placeholder="password">
						</div>
						<div class="pager">
							<button type="submit" class="btn btn-default" style="width:350px;margin: 20px 0 10px 0;">Login in</button>
						</div>
						
					</div>
				</form>
				
			</div>
			<div class="col-md-8 content" >
				<div id="myCarousel" class="carousel slide" style="width:100%;height:100%;">
				    <!-- 轮播（Carousel）指标 -->
				    <ol class="carousel-indicators">
				        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				        <li data-target="#myCarousel" data-slide-to="1"></li>
				        <li data-target="#myCarousel" data-slide-to="2"></li>
				    </ol>   
				    <!-- 轮播（Carousel）项目 -->
				    <div class="carousel-inner">
				        <div class="item active">
				            <img src="img/slide1.png" class="img-responsive" alt="Responsive image" style="width:100%;height:100%;" alt="First slide">
				            <div class="carousel-caption">title1</div>
				        </div>
				        <div class="item">
				            <img src="img/slide1.png" class="img-responsive" alt="Responsive image" style="width:100%;height:100%;" alt="Second slide">
				            <div class="carousel-caption">title 2</div>
				        </div>
				        <div class="item">
				            <img src="img/slide1.png" class="img-responsive" alt="Responsive image" style="width:100%;height:100%;" alt="Third slide">
				            <div class="carousel-caption">title 3</div>
				        </div>
				    </div>
				    <!-- 轮播（Carousel）导航 -->
				    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				        <span class="sr-only">Previous</span>
				    </a>
				    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				        <span class="sr-only">Next</span>
				    </a>
				</div>
			</div>
		</div>
		
	</div>
    
  </body>
</html>