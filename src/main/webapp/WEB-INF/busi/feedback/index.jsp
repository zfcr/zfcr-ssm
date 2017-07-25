<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>留言板 - 章锋博客管理网站</title>
<jsp:include page="/common/header.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/framework/qqface/qqface.css" />
<script type="text/javascript">
function replace_em(str){
	str = str.replace(/\</g,'&lt;');
	str = str.replace(/\>/g,'&gt;');
	str = str.replace(/\n/g,'<br/>');
	str = str.replace(/\[em_([0-9]*)\]/g,'<img src="${ctx}/framework/qqface/face/$1.gif" border="0" />');
	return str;
}
</script>
</head>
<body class="body">
    <nav class="navbar navbar-inverse" role="navigation" style="margin-bottom:0px;">
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
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
    
    
    <div class="container">
	    <div class="row clearfix">
	        <div class="col-md-8 column" style="margin-right: 0px;padding-top: 20px;padding-right:10px;">
	          <div style="margin: 0px auto;width: 100%;">
		        <div style="overflow: auto;white-space:normal;word-break:break-all;" id="titleDiv">
		            <div>
		              <p class="bg-info bg-heading" style="padding: 15px;">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-edit"></span>&nbsp;留言</p>
		            </div>
		            <div class="jumbotron bg-content" style="padding: 10px 10px;">
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
                          <span class="emotion">表情</span>
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
                      <p class="bg-info bg-heading" style="padding: 15px;">
                      	&nbsp;&nbsp;&nbsp;
                      	<span class="glyphicon glyphicon-edit"></span>
                      	&nbsp;留言列表
                      </p>
                    </div>
                    
                    <ul class="list-group">
					  <s:iterator value="#request.feedbackInfos" var="feedbackInfo">
						  <li class="list-group-item bg-content">
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
							  					<script type="text/javascript">
								              		document.write(replace_em('${feedbackInfo.message }'));
								              	</script>
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
									  					<script type="text/javascript">
										              		document.write(replace_em('${children.message }'));
										              	</script>
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
	        	<div class="panel panel-default zf-panel new-title">
					<div class="panel-heading">
						个人信息
					</div>
					<div class="panel-body">
						<div style="height: 5px;"></div>
						<span>&nbsp;姓名： 章锋</span>	
						<div style="height: 5px;"></div>
						<span>&nbsp;昵称： 小码锋</span>
						<div style="height: 5px;"></div>
						<span>&nbsp;访问： ${visitTimes }次</span>
						<div style="height: 5px;"></div>
						<span>&nbsp;留言： ${feedbacks }次</span>
						<div style="height: 5px;"></div>
						<span>&nbsp;作品： 章锋</span>
						<div style="height: 5px;"></div>
						<span>&nbsp;邮箱： zhangfeng2124@163.com</span>
						<div style="height: 5px;"></div>
						<span>&nbsp;QQ： 568807544</span>
						<div style="height: 5px;"></div>
						<div align="center">
							<img alt="我的微信号" src="${ctx }/images/weixin.jpg" width="200">
						</div>
						<div style="height: 5px;"></div>
					</div>
				</div>
				
				<div style="height: 5px;"></div>
	        
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
	            				<a href="${ctx }/blog/category/${titleTypeNum.code}" class="list-group-item" 
	            				   style="background-color: #C8D190;border: 0px;border-bottom: 2px solid rgba(168, 205, 37, 1);">
								  	<i class="fa fa-plus"></i>
								  	&nbsp;${titleTypeNum.name }
								  	<span class="badge" style="background-color: #468847;">${titleTypeNum.cn }</span>
								</a>
	            			</s:iterator>
						</ul>
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
<script type="text/javascript" src="${ctx}/framework/qqface/jquery.qqFace.js"></script>
<script type="text/javascript" src="${ctx}/framework/qqface/jquery-browser.js"></script>
<script type="text/javascript">
// 	var flag = "${flag}";
// 	if(flag == "goback"){
// 		$.bootstrapGrowl("留言提交成功！",{type:"success"});
// 	}

	$('.emotion').qqFace({
    	id : 'facebox', //表情盒子的ID
    	assign:'entity.message', //给那个控件赋值
    	path:'${ctx}/framework/qqface/face/'	//表情存放的路径
    });

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
    				$.bootstrapGrowl("留言提交成功！",{type:"success"});
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
    				$.bootstrapGrowl("留言提交成功！",{type:"success"});
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
        +'<span id="emotion_'+id+'" class="emotion">表情</span>'
        +'<div class="input-group">'
        +'  <span class="input-group-addon"></span>'
        +'  <textarea id="message_'+id+'" name="entity.message" class="form-control validate[required,maxSize[1000]]"'
        +'   rows="4" placeholder="说点什么吧(必填)..."></textarea>'
        +'</div>'
        +'<br>'
        +'<div class="input-group" style="margin: 0px auto;">'
        +'  <a class="button button-primary button-rounded button-small" onclick="submitReply(\''+id+'\')" href="###">提交</a>'
        +'</div>'
        +'</form>'
        +'</div>';
    	$(obj).closest("table").after(replyHtml);
    	
    	$('#emotion_'+id).qqFace({
        	id : 'facebox', //表情盒子的ID
        	assign:'message_'+id, //给那个控件赋值
        	path:'${ctx}/framework/qqface/face/'	//表情存放的路径
        });
    }
    
    function replace_em(str){
    	str = str.replace(/\</g,'&lt;');
    	str = str.replace(/\>/g,'&gt;');
    	str = str.replace(/\n/g,'<br/>');
    	str = str.replace(/\[em_([0-9]*)\]/g,'<img src="${ctx}/framework/qqface/face/$1.gif" border="0" />');
    	return str;
    }
</script>
</html>