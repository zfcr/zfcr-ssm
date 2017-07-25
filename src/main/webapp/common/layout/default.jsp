<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title><sitemesh:title/> - 章锋博客管理网站</title>
<sitemesh:head/>
</head>
<body class="body">

    <!-- 顶部固定区域 begin -->
    <nav class="navbar navbar-inverse" role="navigation" style="margin-bottom:0px;">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="${ctx }/" style="margin: 0px;padding: 0px;padding-top: 3px;">
	        <img alt="" src="${ctx }/common/images/logo.png">
	      </a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <p class="navbar-text navbar-left">
            <span style="FILTER: blur(direction=135,strength=8)" align="center"><font class="header-title" face="黑体" size="4"><b><i>章锋博客管理网站</i></b></font></span>
          </p>
	      <p class="navbar-text navbar-right" style="width: 200px;"></p>
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="${ctx }">技术文章</a></li>
	        <li><a href="${ctx }/blog/feedback/index">留言板</a></li>
	      </ul>
	    </div>
	  </div>
	</nav>
	<!-- 顶部固定区域 end -->
	
    <sitemesh:body/>
    
    <div class="footer" align="center">
        <hr>
        <p>© 2017 章锋博客管理网站（zhangfeng.com）</p>
    </div>
</body>
</html>