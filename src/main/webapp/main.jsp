<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>章锋个人博客</title>
<jsp:include page="/common/header.jsp"></jsp:include>
<style type="text/css">
	.navbar {
		margin-bottom: 5px;
	}
</style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
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
       <a class="navbar-brand" href="${ctx }/" style="margin: 0px;margin-left: 15px; padding: 0px;padding-top: 8px;">
      	<img alt="" src="${ctx }/common/images/zi.png">
      </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <p class="navbar-text navbar-right" style="width: 200px;"></p>
      <form class="navbar-form navbar-right" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
         <a class="button button-primary button-rounded button-small" href="#">Go</a>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">程序人生</a></li>
        <li><a href="#">生活点滴</a></li>
        <li><a href="#">娱乐天地</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div style="border: 1px solid #e7e7e7;width: expression(body.width);overflow: hidden;">
	<img alt="" src="${ctx }/common/images/main-flag.png">
</div>
</body>
</html>