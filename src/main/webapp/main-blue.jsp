<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>章锋个人博客</title>
<jsp:include page="/common/header.jsp"></jsp:include>
</head>
<body class="body" style="background-color: #8BD2E4;">
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
        <li><a href="${ctx }/blog/feedback/index">留言板</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


<div style="height: 5px;"></div>

<div class="container">
	<div style="border: 1px solid #e7e7e7;width: expression(body.width);overflow: hidden;">
		<img alt="" src="${ctx }/common/images/test.png" class="img-responsive" style="max-width: 100%;">
	</div>
	<div style="height: 5px;"></div>
    <div class="row clearfix">
        <div class="col-md-8 column"> <!-- border-right: 3px double red; -->
        	<div class="jumbotron" style="padding: 10px;background-color: #AFDFEC;">
        	<div class="row">
        		<div class="col-md-6">
        			<figure style="width: 40%;float: left;overflow: hidden;">
        				<a href="#" onclick="">
        					<img class="img-responsive" alt="" src="${ctx }/blog/images/default.png">
        				</a>
        			</figure>
        			<div style="width: 60%;float: left;">MySql 自增列的使用</div>
        		</div>
        		<div class="col-md-6">
        			<figure style="width: 40%;float: left;overflow: hidden;">
        				<a href="#" onclick="">
        					<img class="img-responsive" alt="" src="${ctx }/blog/images/default.jpg">
        				</a>
        			</figure>
        			<div style="width: 60%;float: left;">MySql 自增列的使用</div>
        		</div>
        	</div>
        	<div class="row" style="height: 10px;"></div>
        	<div class="row">
        		<div class="col-md-6">
        			<figure style="width: 40%;float: left;overflow: hidden;">
        				<a href="#" onclick="">
        					<img class="img-responsive" alt="" src="${ctx }/blog/images/default.png">
        				</a>
        			</figure>
        			<div style="width: 60%;float: left;">MySql 自增列的使用</div>
        		</div>
        		<div class="col-md-6">
        			<figure style="width: 40%;float: left;overflow: hidden;">
        				<a href="#" onclick="">
        					<img class="img-responsive" alt="" src="${ctx }/blog/images/default.jpg">
        				</a>
        			</figure>
        			<div style="width: 60%;float: left;">MySql 自增列的使用</div>
        		</div>
        	</div>	</div>
        
       	    <div style="clear: both;height: 5px;"></div>
       	    
          <s:iterator value="#request.blogInfos" var="blogInfo">
                <blockquote>
					<table style="overflow: hidden;">
						<tr>
							<td><img class="blog-main-img" alt="${blogInfo.title }" src="${blogInfo.imagePath }"></td>
							<td style="padding-left: 15px; vertical-align: top;" valign="top">
								<div><a href="${ctx }/blog/show/${blogInfo.id}" target="_blank">${blogInfo.title }</a></div>
								<div class="font-summary">
									<span class="glyphicon glyphicon-time"></span>
									<span><s:date name="#blogInfo.createTime" format="yyyy-MM-dd"/></span>

									<span class="space10"></span>
									
									<span class="glyphicon glyphicon-user"></span>
									<span>${blogInfo.createUser }</span>
									
									<span class="space10"></span>
									
									<span class="glyphicon glyphicon-eye-open"></span>
									<span>阅读(${blogInfo.visitTimes })</span>

									<span class="space10"></span>
									
									<span class="glyphicon glyphicon-comment"></span>
									<span>评论(${blogInfo.comments })</span>
								</div>
								<div style="height: 5px;"></div>
								<div class="font-summary hidden-xs hidden-sm" style="height: 100px; overflow: hidden;word-wrap: break-word;white-space:normal;word-break:break-all;">
									${blogInfo.summary }
								</div>
							</td>
						</tr>
					</table>
			 </blockquote>
			 
			 <div style="height: 5px;"></div>
			 
          </s:iterator>
		  
		  <div align="right">
		    <nav aria-label="Page navigation">
			  <ul class="pagination">
			    <li>
			      <a href="${ctx }/blog/1" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <s:iterator begin="1" end="#request.blogInfos.getTotalPage()" var="pageIndex">
			     <li 
			         <s:if test="#pageIndex == #request.blogInfos.getPage()">class="active"</s:if>
			     ><a href="${ctx }/blog/${pageIndex}">${pageIndex}</a></li>
			    </s:iterator>
			    <li>
			      <a href="${ctx }/blog/${blogInfos.getTotalPage() }" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>	
	       </div>
        </div>
        <div class="col-md-4 column hidden-xs hidden-sm">
            <div class="panel panel-default">
               <div class="panel-heading">
                   	最新文章
               </div>
               <div class="panel-body titleGrid" style="padding-top: 0px;background-color: #AFDFEC;">
                    <s:iterator value="#request.newBlogInfos" var="newBlogInfo" status="blogCount">
                        <div style="border-bottom: 1px dotted #ddd;">
	                        <div class="number 
	                           <s:if test="#blogCount.count == 1">one</s:if>
	                           <s:elseif test="#blogCount.count == 2">two</s:elseif>
	                           <s:elseif test="#blogCount.count == 3">three</s:elseif>
	                           <s:elseif test="#blogCount.count == 4">four</s:elseif>
	                           <s:elseif test="#blogCount.count == 5">five</s:elseif>
	                           <s:elseif test="#blogCount.count == 6">six</s:elseif>
	                           <s:elseif test="#blogCount.count == 7">seven</s:elseif>
	                           " style="float: left;margin-right: 10px;margin-top: 8px;">${blogCount.count }</div>
	                        <div class="apostrophe" style="padding-top: 10px;">
	                            <nobr><a href="${ctx }/blog/show/${newBlogInfo.id }" target="_blank">${newBlogInfo.title }</a></nobr>
	                        </div>
	                    </div>
                    </s:iterator>
                </div>
            </div>
            <div style="height: 50px;"></div>
                <div style="width: 350px;margin: 0px auto;">
                   <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <!-- 轮播（Carousel）指标 -->
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ol>   
                        <!-- 轮播（Carousel）项目 -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="${ctx }/common/images/gg-img1.png" alt="First slide">
                            </div>
                            <div class="item">
                                <img src="${ctx }/common/images/gg-img2.png" alt="Second slide">
                            </div>
                            <div class="item">
                                <img src="${ctx }/common/images/gg-img3.png" alt="Third slide">
                            </div>
                        </div>
                        <!-- 轮播（Carousel）导航 -->
                        <a class="carousel-control left" href="#myCarousel" 
                            data-slide="prev">&lsaquo;
                        </a>
                        <a class="carousel-control right" href="#myCarousel" 
                            data-slide="next">&rsaquo;
                        </a>
                    </div>
                </div>
        </div>
    </div>
    
</div>

    <div class="footer" align="center">
        <hr>
        <p>  © 2017 章锋个人博客（zhangfeng.com）</p>
    </div>
</body>
</html>