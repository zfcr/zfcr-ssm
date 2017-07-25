<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>${entity.title }</title>
<jsp:include page="/common/header.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/framework/ckeditor/vendor/ckeditor/plugins/codesnippet/lib/highlight/styles/monokai_sublime.css" />
<link rel="stylesheet" href="${ctx}/framework/qqface/qqface.css" />
<style type="text/css">
pre {
    position: relative;
    margin-bottom: 24px;
    border-radius: 3px;
    border: 1px solid #C3CCD0;
    background: #FFF;
    overflow: hidden;
}

code {
  display: block;
  padding: 12px 24px;
  overflow-y: auto;
  font-weight: 300;
  font-family: Menlo, monospace;
  font-size: 0.8em;
}

code.has-numbering {
    margin-left: 21px;
}

.pre-numbering {
    position: absolute;
    top: 0;
    left: 0;
    width: 35px;
    padding: 18px 2px 12px 0;
    border-right: 1px solid #C3CCD0;
    border-radius: 3px 0 0 3px;
    background-color: #EEE;
    text-align: right;
    font-family: Menlo, monospace;
    font-size: 1em;
    color: #AAA;
}
</style>
<style type="text/css">
    hr {
        border-color: #378F07;
    }
</style>
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
<body class="body" style="background-color: #378F07;">
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
    
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <p class="navbar-text navbar-right" style="width: 200px;"></p>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">技术文章</a></li>
            <li><a href="${ctx }/blog/feedback/index">留言板</a></li>
          </ul>
        </div>
      </div>
    </nav>
    
    <div style="height: 5px;"></div>
    
    <div class="container">
	    <div class="row clearfix">
	        <div class="col-md-8 column bg-content">
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
		        
		            <div style="line-height: 25px;">${entity.content }</div>
		            
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
		              	<script type="text/javascript">
		              		document.write(replace_em('${blogComment.comment }'));
		              	</script>
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
					     <label for="blogCommentComment" class="col-sm-2 control-label">评论内容：</label>
					    
					    <div class="col-sm-10">
                          <span class="emotion">表情</span>
                          <textarea id="blogCommentComment" name="blogComment.comment" class="form-control validate[required,maxSize[1000]]" rows="4"></textarea>
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
	        
	        <div class="col-md-4 column hidden-xs hidden-sm">
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
	         
				<div style="height: 5px;"></div>
	    	</div>
	    </div>
	    
	</div>
    
    <div class="footer" align="center">
        <hr>
        <p>  © 2017 章锋个人博客（zhangfeng.com）</p>
    </div>
</body>
<script type="text/javascript"
	src="${ctx}/framework/ckeditor/vendor/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
<script type="text/javascript" src="${ctx}/framework/qqface/jquery.qqFace.js"></script>
<script type="text/javascript" src="${ctx}/framework/qqface/jquery-browser.js"></script>
<script type="text/javascript">
    $("img").css("maxWidth",$("#titleDiv").width());
    
    $(function(){
        $('pre code').each(function(){
            var lines = $(this).text().split('\n').length;
            var $numbering = $('<ul/>').addClass('pre-numbering');
            $(this)
                .addClass('has-numbering')
                .parent()
                .append($numbering);
            for(i=1;i<=lines;i++){
                $numbering.append($('<li/>').text(i));
            }
        });
        
        $('.emotion').qqFace({
    		id : 'facebox', //表情盒子的ID
    		assign:'blogCommentComment', //给那个控件赋值
    		path:'${ctx}/framework/qqface/face/'	//表情存放的路径
    	});
        
    });

	hljs.initHighlightingOnLoad();
    
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
    Common.asyncCallUrl("${ctx}/blog/manage/blogComment-recordVisitTimes?blogId=${entity.id}");
    
</script>
</html>