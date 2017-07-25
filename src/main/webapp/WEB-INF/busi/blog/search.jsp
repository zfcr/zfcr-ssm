<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<jsp:include page="/common/header.jsp"></jsp:include>
<title>文章搜索</title>
<style type="text/css">
	.caption {
		background-color: #C8D190;
		max-height: 40px;
		overflow: hidden;
	}
	
	.thumbnail {
		background-color: #C8D190;
		border-color: #C8D190;
		margin-bottom: 0px;
	}
	
</style>
<script type="text/javascript">

$(function(){
    $("#textKeywords").keydown(function(event){
        if(event.keyCode == 13 && $.trim($("#textKeywords").val()) != ''){
            var keywords = encodeURIComponent($("#textKeywords").val());
            window.location.href = "${ctx}/blog/search?keywords="+encodeURIComponent(keywords);
        }
    });
    
    $("#btnKeywords").on("click", function(){
        if($.trim($("#textKeywords").val()) != ''){
            var keywords = encodeURIComponent($("#textKeywords").val());
            window.location.href = "${ctx}/blog/search?keywords="+encodeURIComponent(keywords);
        }
    });
    
});
</script>
</head>
<body>
<div>
	<img alt="" src="${ctx }/images/pic2.jpg" class="img-responsive" style="max-width: 100%;">
</div>

<div class="container">
	<div style="height: 5px;"></div>
    <div class="row clearfix">
        <div class="col-md-8 column">
       	    <ul class="list-group content">
       	    <s:if test="#request.blogInfos.size() == 0">
       	    	<li class="list-group-item" style="background-color: #C8D190;border-color: rgba(168, 205, 37, 1);">
				  	<table style="overflow: hidden;">
						<tr>
							<td valign="top"><img class="blog-main-img" src="${ctx }/images/pic-sorry.png"></td>
							<td style="padding-left: 15px; vertical-align: middle;" valign="middle">
								<div>没有找到您需要的文章，请整理关键词，重新搜索！</div>
								</td>
							</tr>
					</table>
				</li>
       	    </s:if>
       	    <s:else>
	       	    <s:iterator value="#request.blogInfos" var="blogInfo">
				  <li class="list-group-item" style="background-color: #C8D190;border-color: rgba(168, 205, 37, 1);">
				  	<table style="overflow: hidden;">
							<tr>
								<td valign="top"><img class="blog-main-img" alt="${blogInfo.title }" src="${blogInfo.imagePath }"></td>
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
				 	 </li>
				 </s:iterator>
       	    </s:else>
			</ul>
       	    
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
	        <div id="divAffix">
				<div class="input-group">
					<input id="textKeywords" type="text" class="form-control" autocomplete="off" placeholder="请输入关键字...">
					<span class="input-group-btn">
						<button id="btnKeywords" class="btn btn-default" type="button"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
					</span>
				</div>
	            
	            <div style="height: 10px;"></div>
	            
				<div class="panel panel-default zf-panel new-title">
					<div class="panel-heading">
						最新文章
					</div>
					<div class="panel-body">
						<ul class="list-group titleGrid apostrophe">
							<s:iterator value="#request.newBlogInfos" var="newBlogInfo" status="blogCount">
								<nobr>
									<a class="list-group-item" href="${ctx }/blog/show/${newBlogInfo.id }"
									target="_blank" style="background-color: #C8D190;border: 0px;border-bottom: 1px solid rgba(168, 205, 37, 1);padding-bottom: 5px;padding-top: 10px;">
										<span class="label number 
										   <s:if test="#blogCount.count == 1">one</s:if>
		                                   <s:elseif test="#blogCount.count == 2">two</s:elseif>
		                                   <s:elseif test="#blogCount.count == 3">three</s:elseif>
		                                   <s:elseif test="#blogCount.count == 4">four</s:elseif>
		                                   <s:elseif test="#blogCount.count == 5">five</s:elseif>
		                                   <s:elseif test="#blogCount.count == 6">six</s:elseif>
		                                   <s:elseif test="#blogCount.count == 7">seven</s:elseif>
										">&nbsp;${blogCount.count }&nbsp;</span>
										&nbsp;&nbsp;
										${newBlogInfo.title }
									</a></nobr>
							</s:iterator>
						</ul>
					</div>
				</div>
				
	            <div style="height: 5px;"></div>
	            
	            <div class="panel panel-default zf-panel title-type">
	            	<div class="panel-heading">
	                   	文章分类
	                </div>
	            	<div class="panel-body">
	            		<ul class="list-group">
	            			<s:iterator value="#request.titleTypeNums" var="titleTypeNum">
	            				<a href="#" class="list-group-item" style="background-color: #C8D190;border: 0px;border-bottom: 2px solid rgba(168, 205, 37, 1);">
								  	<i class="fa fa-plus"></i>
								  	&nbsp;${titleTypeNum.name }
								  	<span class="badge" style="background-color: #468847;">${titleTypeNum.cn }</span>
								</a>
	            			</s:iterator>
						</ul>
	            	</div>
	       	 	</div>
	         
			<div style="height: 5px;"></div>
			
        </div>
    </div>
    </div>
</div>
</body>
</html>