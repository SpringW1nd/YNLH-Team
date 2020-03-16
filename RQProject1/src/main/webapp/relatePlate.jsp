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
	<link rel="stylesheet" href="bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
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
    	function goBackToReservation(){
    		$(location).attr('href', 'aaa/reservationList');
    	}
    	function sighout(){
			$(location).attr('href', 'aaa/sighout');
			
		}
    </script>
  </head>
  <body>
    

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="jquery/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="bower_components/moment/min/moment.min.js"></script>
    <script type="text/javascript" src="bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
	<div class="container-fluid outer" >
		<div class="row header	" >
			<div class="col-md-2" >
				<img src="img/wsu-home-logo.svg" class="img-responsive" alt="Responsive image">
			</div>
			<div class="col-md-9" style="padding: 10px 0 0 0;">
				<ul class="nav nav-pills">
				  <li role="presentation " ><a class="navigation" href="aaa/aab?username=123&password=123">Home</a></li>
				  <li role="presentation " class="active"><a>Reservation</a></li>
				  <li role="presentation "><a class="navigation" href="aaa/aab?username=123&password=123">Contract</a></li>
				  <li role="presentation "><a class="navigation" href="aaa/aab?username=123&password=123">Bill</a></li>
				  <li role="presentation "><a class="navigation" href="aaa/Operation">Operation</a></li>
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
				<div class="panel-heading">Make A Reservation</div>
				<div class="panel-body">
					
						<form type="post" action="aaa/reservation">
							<div class="row" style="margin:15px 0 0 0; ">
								<div class="col-md-2"></div>
								<div class="col-md-4">
									<div class="form-group">
		  								<label for="name">Name</label>
		    							<input type="text" class="form-control" id="name" name="name" placeholder="name" value="Max" readonly="readonly">
		  							</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
		  								<label for="plateNumber">Plate Number</label>
		    							<input list="numberList" class="form-control" id="plateNumber" name="plateNumber" placeholder="plateNumber"  type="text">
										<datalist id="numberList">
										    <option value="123">123</option>
										    <option value="234">234</option>
										</datalist>
		  							</div>
								</div>
								<div class="col-md-2"></div>
							</div>
							<div class="row" >
								<div class="col-md-2"></div>
								<div class="col-md-4">
									<div class="form-group">
		  								<label for="RStartDate">Start Time</label>
		    							<div class='input-group date' id='RStartDateSel'>
							                <input type='text' class="form-control" id="RStartDate" name="RStartDate" />
							                <span class="input-group-addon">
							                    <span class="glyphicon glyphicon-calendar"></span>
							                </span>
							            </div>
		  							</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
		  								<label for="REndDate">End Time</label>
		    							<div class='input-group date' id='REndDateSel'>
							                <input type='text' class="form-control" id="REndDate" name="REndDate" />
							                <span class="input-group-addon">
							                    <span class="glyphicon glyphicon-calendar"></span>
							                </span>
							            </div>
		  							</div>
								</div>
								<div class="col-md-2"></div>	
							</div>
  							<div class="form-group ">
  								<div class="row">
  									<div class="col-md-2"></div>
  									<div class="col-md-2"></div>
  									<div class="col-md-2"><button type="submit" class="btn btn-default">Submit</button></div>
  									<div class="col-md-2"><button type="button" class="btn btn-default" onClick="goBackToReservation();">Cancle</button></div>
  									<div class="col-md-2"></div>
  									<div class="col-md-2"></div>
  								</div>
  								
  							</div>
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