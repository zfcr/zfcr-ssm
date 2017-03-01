<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>500 Error Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href='http://fonts.googleapis.com/css?family=Capriola' rel='stylesheet' type='text/css'>
<style type="text/css">
body{
	font-family: 'Capriola', sans-serif;
}
body{
	background:#DAD6CC;
}	
.wrap{
	margin:0 auto;
	width:1000px;
}
.logo h1{
	font-size:200px;
	color:#FF7A00;
	text-align:center;
	margin-bottom:1px;
	text-shadow:4px 4px 1px white;
}	
.logo p{
	color:#B1A18D;;
	font-size:20px;
	margin-top:1px;
	text-align:center;
}	
.logo p span{
	color:lightgreen;
}	
.sub a{
	color:#ff7a00;
	text-decoration:none;
	padding:5px;
	font-size:13px;
	font-family: arial, serif;
	font-weight:bold;
}	
.footer{
	color:white;
	position:absolute;
	right:10px;
	bottom:10px;
}	
.footer a{
	color:#ff7a00;
}	
</style>
</head>

<body>
	<div class="wrap">
		<div class="logo">
			<h1>500</h1>
			<p>服务器异常，异常原因：<s:property value="exception.message"/></p>
			<div class="sub">
			   <p><a href="###" onclick="goback();return false;"> 返回博客首页</a></p>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
    function goback(){
    	var pathName=window.document.location.pathname;
    	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    	window.location.href = projectName;
    }
</script>
</html>