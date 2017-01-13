<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>章锋个人博客</title>
<jsp:include page="/common/header.jsp"></jsp:include>
</head>
<body class="body">
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
	<img alt="" src="${ctx }/common/images/test.png" style="max-width: 100%;">
</div>

<div style="height: 5px;"></div>

<div class="container" style="width: 1200px;min-width: 1200px;">
    <div class="row clearfix">
        <div class="col-md-8 column">
          <div class="page-header">
            <span class="label label-success">推荐文章</span>
          </div>
          
          <div style="height: 5px;"></div>
          
          <blockquote>
                    <table style="height: 200px;">
                        <tr>
                            <td rowspan="2">
                                <img alt="" src="/zfcr-ssm/blog/images/27088c1befe84ba199b1aaf4bd4a7de9.jpeg" width="180" height="120">
                            </td>
                            <td style="padding-left: 15px;">
                                <div>Eclipse tab键用空格代替</div>
                                <div class="font-summary">
                                    <span class="glyphicon glyphicon-time"></span>
                                    <span>2016年01月13日</span>
                                    
                                    <span class="space10"></span>
                                    
                                    <span class="glyphicon glyphicon-user"></span>
                                    <span>章锋</span>
                                    
                                    <span class="space10"></span>
                                    
                                    <span class="glyphicon glyphicon-eye-open"></span>
                                    <span>阅读(10)</span>
                                    
                                    <span class="space10"></span>
                                    
                                    <span class="glyphicon glyphicon-comment"></span>
                                    <span>评论(10)</span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-left: 15px;vertical-align: top;" valign="top">
                                <p class="font-summary">
	                                Eclipse tab键用空格代替两种方式（不确定哪个版本设置哪种方式，我就两种都设了）： 第一种： Window-->Preferences-->General-->Editors-->Text Editors 选项Insert spaces for tabs 勾上，然后 Displayed tab width =4（一般使用4个空格代替）
                                </p>
                            </td>
                        </tr>
                    </table>
          </blockquote>
          
          <div style="height: 5px;"></div>
          
          <blockquote>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.
                </p> <small>Someone famous <cite>Source Title</cite></small>
          </blockquote>
          
          <div style="height: 5px;"></div>
          
          <blockquote>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.
                </p> <small>Someone famous <cite>Source Title</cite></small>
          </blockquote>
        </div>
        <div class="col-md-4 column">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">最新文章</h3>
                </div>
                <div class="panel-body">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>