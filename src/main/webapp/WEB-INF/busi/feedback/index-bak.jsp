<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>留言板</title>
<jsp:include page="/common/header.jsp"></jsp:include>
</head>
<body class="body" style="background-color: rgb(240, 235, 230);">
    <nav class="navbar navbar-default" role="navigation">
      <div class="container-fluid" style="">
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
            <li><a href="${ctx }/blog/feedback/index">留言板</a></li>
          </ul>
        </div>
      </div>
    </nav>
    
    
    <div class="container">
	    <div class="row clearfix">
	        <div class="col-md-8 column" style="margin-right: 0px;padding-top: 20px;padding-right:10px;">
	          <div style="margin: 0px auto;width: 100%;">
		        <div style="overflow: auto;white-space:normal;word-break:break-all;" id="titleDiv">
		            <div>
		              <p class="bg-info" style="padding: 15px;background-color: rgba(217, 237, 247, 1)">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-edit"></span>&nbsp;留言</p>
		            </div>
		            <div class="jumbotron" style="background-color: rgba(217, 237, 247, 0.5);padding: 10px 10px;">
	                    <form id="feedbackForm" class="form-horizontal blog-form" role="form" style="margin: 0px auto;">
						    <div class="input-group">
					            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					            <input type="text" class="form-control validate[required]" maxlength="100" id="entity.userName" name="entity.userName" placeholder="请输入您的姓名或昵称(必填)...">
					        </div>
					        <br>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="fa fa-envelope-o"></span></span>
                                <input type="text" class="form-control validate[custom[email]]" id="entity.email" name="entity.email" placeholder="请输入您的邮箱地址...">
                            </div> 
                            <br>
					        <div class="input-group">
                                <span class="input-group-addon"><span class="fa fa-sitemap"></span></span>
                                <input type="text" class="form-control" id="entity.website" maxlength="100" name="entity.website" placeholder="请输入您的网站或博客链接...">
                            </div> 
                            <br>
						  <div class="input-group">
						  	  <span class="input-group-addon"></span>
	                          <textarea id="entity.message" name="entity.message" class="form-control validate[required,maxSize[1000]]"
	                           rows="4" placeholder="说点什么吧(必填)..."></textarea>
						  </div>
						  <br>
						  <div class="input-group" style="margin: 0px auto;">
						      <a id="submit" class="button button-primary button-rounded button-small" href="#">提交</a>
						  </div>
						</form>
    				</div>
		            <div style="height: 5px;"></div>
		        
		            <div>
                      <p class="bg-info" style="padding: 15px;background-color: rgba(217, 237, 247, 1)">
                      	&nbsp;&nbsp;&nbsp;
                      	<span class="glyphicon glyphicon-edit"></span>
                      	&nbsp;留言列表
                      </p>
                    </div>
                    
                    <ul class="list-group">
					  <s:iterator value="#request.feedbackInfos" var="feedbackInfo">
						  <li class="list-group-item" style="background-color: rgba(217, 237, 247, 0.3)">
						  	<table>
						  		<tr>
						  			<td width="64" height="64" style="vertical-align: top;" valign="top">
						  				<div style="width: 62px;height: 62px;border: 1px solid #ccc;background-color: #ccc;">
								  			<img alt="" src="${ctx }/images/feedback/${feedbackInfo.icon}" width="60" height="60">
								  		</div>
						  			</td>
						  			<td style="padding-left: 10px;width: 100%;">
						  				<div>
							  				<span class="glyphicon glyphicon-user"></span>${feedbackInfo.userName }
							  				&nbsp;&nbsp;
							  				<span class="glyphicon glyphicon-time"></span><s:date name="#feedbackInfo.createTime" format="yyyy-MM-dd HH:mm:ss"/>
							  				&nbsp;&nbsp;
							  				<a style="float: right;" href="###" onclick="replyFeedback(this,'${feedbackInfo.id}');return false;">
						  						回复
						  					</a>
							  			</div>
							  			<div style="height: 5px;"></div>
							  			<div>
							  				<span>
							  					${feedbackInfo.message }
							  				</span>
							  			</div>
						  			</td>
						  		</tr>	
						  	</table>
						  	<s:if test="#feedbackInfo.childrens.size > 0">
						  		<s:iterator value="#feedbackInfo.childrens" var="children" status="childrenStatus">
							  		<div style="height: 5px;"></div>
								  	<table style="margin-left: ${children.levelNumber < 2 ? children.levelNumber*30 : 60 }px;">
								  		<tr><td colspan="2" style="height: 5px;border-top: 1px solid gray;"></td></tr>
								  		<tr>
								  			<td width="64" height="64" style="vertical-align: top;" valign="top">
								  				<div style="width: 62px;height: 62px;border: 1px solid #ccc;background-color: #ccc;">
										  			<img alt="" src="${ctx }/images/feedback/${children.icon}" width="60" height="60">
										  		</div>
								  			</td>
								  			<td style="padding-left: 10px;width: 100%;">
								  				<div>
									  				<span class="glyphicon glyphicon-user"></span>${children.userName }
									  				&nbsp;&nbsp;
									  				<span class="glyphicon glyphicon-time"></span><s:date name="#children.createTime"/>
									  				&nbsp;&nbsp;
									  				回复${children.parentUserName }的留言
									  				<a style="float: right;" href="###" onclick="replyFeedback(this,'${children.id}');return false;">
									  					回复
									  				</a>
									  			</div>
									  			<div style="height: 5px;"></div>
									  			<div>
									  				<span>
									  					${children.message }
									  				</span>
									  			</div>
								  			</td>
								  		</tr>	
								  	</table>
						  		</s:iterator>
						  	</s:if>
						  </li>
					  </s:iterator>
					</ul>
                    
		        </div>
		      </div>
	        </div>
	        
	        <div class="col-md-4 column hidden-xs hidden-sm" style="padding-top: 20px;">
	            <div>
	                <div>
                      <p class="bg-info" style="padding: 15px;background-color: rgba(217, 237, 247, 1)">
                      	&nbsp;&nbsp;&nbsp;
                      	最新文章
                      </p>
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
<script type="text/javascript" src="${ctx }/framework/bootstrap-growl/jquery.bootstrap-growl.min.js"></script>
<script type="text/javascript">
// 	var flag = "${flag}";
// 	if(flag == "goback"){
// 		$.bootstrapGrowl("留言提交成功！",{type:"success"});
// 	}

    $("img").css("maxWidth",$("#titleDiv").width());
    
    $("#submit").bind("click",function(){
    	$("#feedbackForm").validationEngine({
    		autoHidePrompt: true,
    		autoHideDelay: 5000 // 单位毫秒（ms）
    	});
    	var result = $("#feedbackForm").validationEngine('validate');
        if(result){
        	$("body").mLoading({text:'提交中...'});
    		$.ajax({
    			url: "${ctx}/blog/feedback/saved",
    			data:$('#feedbackForm').serialize(),
    			method:'POST',
    			dataType: "text",
    			success:function(data){
    				goback();
    			},
    			error:function(jqXHR, textStatus, errorThrown){
    				$("body").mLoading('hide');
    				$.bootstrapGrowl("请求失败，请联系管理员！");
    			}
    		});
        }
    });
    
    function submitReply(id){
    	$("#form-"+id).validationEngine({
    		autoHidePrompt: true,
    		autoHideDelay: 5000 // 单位毫秒（ms）
    	});
    	var result = $("#form-"+id).validationEngine('validate');
    	$("#entity\\.upId").val(id);
        if(result){
        	$("body").mLoading({text:'提交中...'});
    		$.ajax({
    			url: "${ctx}/blog/feedback/saveReply",
    			data:$('#form-'+id).serialize(),
    			method:'POST',
    			dataType: "text",
    			success:function(data){
    				goback();
    			},
    			error:function(jqXHR, textStatus, errorThrown){
    				$("body").mLoading('hide');
    				$.bootstrapGrowl("请求失败，请联系管理员！");
    			}
    		});
        }
    }
    
    function goback(){
    	window.location.href = '${ctx}/blog/feedback/index';
    }
    
    function replyFeedback(obj, id){
    	if($("#replyDynamicHtml")){
    		$("#replyDynamicHtml").remove();
    	}
    	var replyHtml = '<div id="replyDynamicHtml" class="jumbotron" style="background-color: rgba(217, 237, 247, 0.5);padding: 10px 10px;">'
        +'    <form id="form-'+id+'" class="form-horizontal blog-form" role="form" style="margin: 0px auto;">'
        +'    	<input type="hidden" id="entity.upId" name="entity.upId" >'
        +'<div class="input-group">'
        +'    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>'
        +'    <input type="text" class="form-control validate[required]" maxlength="100" id="entity.userName" name="entity.userName" placeholder="请输入您的姓名或昵称(必填)...">'
        +'</div>'
        +'<br>'
        +'<div class="input-group">'
        +'    <span class="input-group-addon"><span class="fa fa-envelope-o"></span></span>'
        +'    <input type="text" class="form-control validate[custom[email]]" id="entity.email" name="entity.email" placeholder="请输入您的邮箱地址...">'
        +'</div>' 
        +'<br>'
        +'<div class="input-group">'
        +'    <span class="input-group-addon"><span class="fa fa-sitemap"></span></span>'
        +'    <input type="text" class="form-control" id="entity.website" maxlength="100" name="entity.website" placeholder="请输入您的网站或博客链接...">'
        +'</div>' 
        +'<br>'
        +'<div class="input-group">'
        +'  <span class="input-group-addon"></span>'
        +'  <textarea id="entity.message" name="entity.message" class="form-control validate[required,maxSize[1000]]"'
        +'   rows="4" placeholder="说点什么吧(必填)..."></textarea>'
        +'</div>'
        +'<br>'
        +'<div class="input-group" style="margin: 0px auto;">'
        +'  <a class="button button-primary button-rounded button-small" onclick="submitReply(\''+id+'\')" href="###">提交</a>'
        +'</div>'
        +'</form>'
        +'</div>';
    	$(obj).closest("table").after(replyHtml);
    }
</script>
</html>