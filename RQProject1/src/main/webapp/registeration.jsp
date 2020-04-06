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
    </style>
    <script type="text/javascript">
    	function goBackIndex(){
        		$(location).attr('href', '');
    	}
    	function submitFrom(){
    		if($("#password").val()!=$("#comfirmPw").val()){
    			alert("The password entered twice is inconsistent!!");
    		}else{
    			$("form").submit();
    		}
    		
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
				
			</div>
		</div>
		<div class="row " >
			<div class="col-md-2 " >
			</div>
			<div class="col-md-8 " >
				<div class="panel-heading">Registeration</div>
				<div class="panel-body">
					<form method="post" action="user/registeration">
						<div class="row" style="margin:15px 0 0 0; ">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="form-group">
	  								<label for="name">User Name</label>
	    							<input type="text" class="form-control" id="username" name="username" placeholder="username"" >
		  						</div>
							</div>
							<div class="col-md-2"></div>
						</div>
						<div class="row" style="margin:15px 0 0 0; ">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="form-group">
	  								<label for="password">Password</label>
	    							<input type="password" class="form-control" id="password" name="password" placeholder="password" >
		  						</div>
							</div>
							<div class="col-md-2"></div>
						</div>
						<div class="row" style="margin:15px 0 0 0; ">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="form-group">
	  								<label for="comfirmPw">Comfirm Password</label>
	    							<input type="password" class="form-control" id="comfirmPw" name="comfirmPw" placeholder="comfirmPw" >
		  						</div>
							</div>
							<div class="col-md-2"></div>
						</div>
						<div class="row" style="margin:15px 0 0 0; ">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="form-group">
	  								<label for="name">Name</label>
	    							<input type="text" class="form-control" id="name" name="name" placeholder="name" >
		  						</div>
							</div>
							<div class="col-md-2"></div>
						</div>
						<div class="row" style="margin:15px 0 0 0; ">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="form-group">
	  								<label for="phoneNumber">Phone Number</label>
	    							<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="phoneNumber" >
		  						</div>
							</div>
							<div class="col-md-2"></div>
						</div>
						<div class="row" style="margin:15px 0 0 0; ">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="form-group">
	  								<label for="email">Email</label>
	    							<input type="text" class="form-control" id="email" name="email" placeholder="email" >
		  						</div>
							</div>
							<div class="col-md-2"></div>
						</div>
						<div class="form-group ">
  								<div class="row">
  									<div class="col-md-2"></div>
  									<div class="col-md-2"></div>
  									<div class="col-md-2"><button type="button" onclick="submitFrom();" class="btn btn-default">Submit</button></div>
  									<div class="col-md-2"><button type="button" class="btn btn-default" onClick="goBackIndex();">Cancle</button></div>
  									<div class="col-md-2"></div>
  									<div class="col-md-2"></div>
  								</div>
  								
  							</div>
					</form>
				</div>
				
				
			</div>
			<div class="col-md-2 " >
			</div>
		</div>
		
	</div>
    
  </body>
</html>