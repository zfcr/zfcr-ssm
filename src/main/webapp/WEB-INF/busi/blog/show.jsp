<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${entity.title }</title>
<jsp:include page="/common/header.jsp"></jsp:include>
</head>
<body class="body">
    <nav class="navbar navbar-default" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only"></span>
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
            <li><a href="#">留言板</a></li>
          </ul>
        </div>
      </div>
    </nav>
    
    
    <div class="container" style="width: 1200px;min-width: 1200px;">
	    <div class="row clearfix">
	        <div class="col-md-8 column" style="margin-right: 0px;padding-right:10px;padding-top: 20px;border-right: 5px dashed #ddd;">
	          <div style="margin: 0px auto;width: 100%;">
		        <div style="overflow: auto;white-space:normal;word-break:break-all;" id="titleDiv">
		            <div align="center" style="font-size: 18px;font-weight: bold;">${entity.title }</div>
		            <div style="height: 5px;"></div>
					<div class="font-summary" align="center">
						<span class="glyphicon glyphicon-time"></span>
						<span>
						  <s:date name="#request.entity.createTime" format="yyyy-MM-dd" />
						</span>
						
						<span class="space10"></span>

						<span class ="glyphicon glyphicon-user"></span>
						<span>${entity.createUser }</span> 
						
						<span class="space10"></span>
						
						<span class="glyphicon glyphicon-eye-open"></span>
						<span>阅读(${entity.visitTimes })</span>

						<span class="space10"></span>
						
						<span class="glyphicon glyphicon-comment"></span>
						<span>评论(${entity.comments })</span>
					</div>
					<hr>
		        
		            ${entity.content }
		            
		            <span class="label label-success">评论列表</span>
		            <hr style="margin-top: 5px;">
		            <s:set var="commentCount" value="#request.blogComments.size()" scope="request"></s:set>
		            <s:iterator value="#request.blogComments" var="blogComment" status="cIndex">
		              <div>
		                  <span class="label label-info">#${commentCount - cIndex.index }楼</span>
		                  <span class="glyphicon glyphicon-time font-summary"></span>
                          <span class="font-summary"><s:date name="#blogComment.lastUpdateDate" format="yyyy-MM-dd HH:mm:ss"/></span>

                          <span class="space10"></span>
                                    
                          <span class="glyphicon glyphicon-user font-summary"></span>
                          <span class="font-summary">${blogComment.nickName }</span>
                          
                          <span class="space10"></span>
                          <a href="#"><span class="glyphicon glyphicon-thumbs-up font-summary" onmouseout="this.style.color='#999'" onmouseover="this.style.color='black'">顶(0)</span></a>
		              </div>
		              <div>
		                  ${blogComment.comment }
		              </div>
		              <hr>
		            </s:iterator>
		            
		            <span class="label label-success">发表评论</span>
		            <hr style="margin-top: 5px;">
                    <form id="formComment" class="form-horizontal" role="form" style="width: 80%;margin: 0px auto;">
                        <input type="hidden" id="blogComment.blogId" name="blogComment.blogId" value="${entity.id }">
					  <div class="form-group">
					    <label for="blogComment.nickName" class="col-sm-2 control-label">昵称：</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" id="blogComment.nickName" name="blogComment.nickName" maxlength="50" placeholder="不填写，系统将随机生成一个昵称">
					    </div>
					  </div>
					  <div class="form-group">
					     <label for="blogComment.comment" class="col-sm-2 control-label">评论内容：</label>
					    
					    <div class="col-sm-10">
                          <textarea id="blogComment.comment" name="blogComment.comment" class="form-control validate[required,maxSize[1000]]" rows="4"></textarea>
                        </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <a id="submitComment" class="button button-primary button-rounded button-small" href="#">发表评论</a>
					    </div>
					  </div>
					</form>
		        </div>
		      </div>
	        </div>
	        
	        <div class="col-md-4 column">
	            <div>
	                <div class="page-header" style="margin-bottom: 0px;">
	                    <span class="label label-success">最新文章</span>
	                </div>
	                <div class="titleGrid">
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
<script type="text/javascript">
    $("img").css("maxWidth",$("#titleDiv").width());
    
    $("#submitComment").bind("click",function(){
    	var result = $("#formComment").validationEngine('validate');
        if(result){
        	Common.save("${ctx}/blog/manage/blogComment-submitComment", "formComment", goback);
        }
    });
    
    function goback(){
    	window.location.href = '${ctx}/blog/show/${entity.id}';
    }
    
    // 记录文章访问次数
    Common.asyncCallUrl("${ctx}/blog/manage/blogComment-recordVisitTimes?blogId=${blogComment.blogId}");
</script>
</html>