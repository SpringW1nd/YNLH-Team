<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- import CSS -->
<link rel="stylesheet" href="./element-ui/lib/theme-chalk/index.css">

</head>
<body style="height: 100%; padding: 0; margin: 0;">
<div class="outer">
    <div class="A"> 1111 </div>
    <div class="B">222 </div>
</div>

</body>
<style>
html,
body { height: 100%; padding: 0; margin: 0; }
.outer { height: 100%; padding: 100px 0 0; box-sizing: border-box ; position: relative; }
.A { height: 100px; background: #BBE8F2; position: absolute; top: 0 ; left: 0 ; width: 100%; }
.B { height: 100%; background: #D9C666; }
.el-header, .el-footer {
	background-color: #B3C0D1;
	color: #333;
	text-align: center;
	line-height: 60px;
	
}

.el-aside {
	background-color: #D3DCE6;
	color: #333;
	text-align: center;
	line-height: 200px;
}

.el-main {
	background-color: #E9EEF3;
	color: #333;
	text-align: center;
	line-height: 160px;
}

body>.el-container {
	height: 100%;
	margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside, .el-container:nth-child(6) .el-aside
	{
	line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
	line-height: 320px;
}
</style>
<!-- import Vue before Element -->
<script src="./vue/vue.js"></script>
<!-- import JavaScript -->
<script src="./element-ui/lib/index.js"></script>

<script>
	new Vue({
		el : '#app',
		data : function() {
			return {
				visible : false
			}
		}
	})
</script>
</html>