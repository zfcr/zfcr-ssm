<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建新博客-使用CKEDITOR框架</title>
<jsp:include page="/common/header.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/framework/bootstrap-fileinput/css/fileinput.min.css">
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
	<div class="panel panel-default" style="margin: 0px auto;min-width: 1200px;width: 80%;background-color: #C8D190;border-color: #9CA94F;">
	    <div class="panel-heading" style="background-color: #9CA94F;border-color: #9CA94F;">
	        <h3 class="panel-title">新建博客（博客新建完成之后，不能马上在博客中查看，需要等博客管理员审核之后才允许查看）</h3>
	    </div>
    	<div class="panel-body">
			<form role="form" id="form1" method="post" enctype="multipart/form-data" autocomplete="off">
			  <s:hidden id="entity.content" name="entity.content"></s:hidden>
			  <s:hidden id="entity.id" name="entity.id"></s:hidden>
			  <div class="form-group">
			    <label for="entity.title">文章标题</label>
			    <s:textfield class="form-control validate[required,maxSize[300]]" id="entity.title" name="entity.title" placeholder="请输入文章标题"></s:textfield>
			  </div>
			  <div class="form-group">
			  	<label for="editor1">文章内容(IE浏览器下，支持粘贴图片)</label>
			    <div>
			     	<textarea id="editor1" name="editor1" data-sample="1" data-sample-preservewhitespace="">
					</textarea>
			    </div>
			  </div>
			  <div class="form-group">
			    <label>
			    	文章分类
			    	<a href="#" class="button button-primary button-rounded button-small" onclick="alertTypeManage()">分类管理</a>
			    </label>
			    <div>
			    	&nbsp;&nbsp;&nbsp;
			    	<s:hidden id="hiddenBlogType" value="%{entity.blogType}"></s:hidden>
			    	<s:iterator value="#request.titleTypes" var="titleType">
			    		<input class="validate[required]" type="radio" onclick="customTypeClick(this)"
			    			name="entity.blogType" value="<s:property value="#titleType.code"/>" />
						<s:property value="#titleType.name"/>
						&nbsp;&nbsp;&nbsp;
			    	</s:iterator>
			    </div>
			  </div>
			  <div class="form-group">
			    <label>
			    	自定义分类（逗号分隔）
			    </label>
			    <div>
			    	<s:textfield class="form-control validate[maxSize[300]]" id="entity.customType" name="entity.customType" 
			    		onblur="reverseSelectCustomType()" placeholder="请输入或选择分类"></s:textfield>
			  		<div id="divCustomType">
			  			
				    </div>
			    </div>
			  </div>
			  <div class="form-group">
                <label for="filePic">显示图标</label>
                <input id="input-image-1" name="filePic" type="file" class="file" accept="image/*">
              </div>
			  <div class="form-group">
			    <label for="entity.blogTag">标签（Tag）</label>
			    <s:textfield class="form-control validate[maxSize[100]]" id="entity.blogTag" name="entity.blogTag" placeholder="多个标签请使用逗号分隔"></s:textfield>
			  </div>
			  <div class="form-group">
			    <label for="entity.summary">摘要</label>
			    <s:textarea class="form-control validate[maxSize[400]]" rows="3" id="entity.summary" name="entity.summary" placeholder="摘要用于在首页显示"></s:textarea>
			  </div>
			  <div class="form-group">
                <label for="entity.summary">创建人</label>
                <s:textfield class="form-control validate[maxSize[50]]" id="entity.createUser" name="entity.createUser" placeholder="请留下您的大名"></s:textfield>
              </div>
			</form>
			
			<div id="buttonGroup" style="margin-top: 10px;margin-bottom: 10px;" align="center">
				<a href="#" class="button button-primary button-rounded button-small" onclick="gosave()">提交</a>
				<a href="#" class="button button-rounded button-small" onclick="goback()">返回首页</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx }/framework/ckeditor/vendor/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/framework/bootstrap-fileinput/js/canvas-to-blob.min.js"></script>
<script type="text/javascript" src="${ctx}/framework/bootstrap-fileinput/js/fileinput.min.js"></script>
<script type="text/javascript">
	$(function(){
		var blogType = $("#hiddenBlogType").val();
		if(blogType != ''){
			$("input[name='entity.blogType']").each(function(){
				if(this.value == blogType){
					this.checked = true;
					loadCustomType(this);
				}
			});
		}
	});


	var config = {
		extraPlugins: 'codesnippet,colorbutton,colordialog',
		codeSnippet_theme: 'monokai_sublime',
		height: 356,
        filebrowserImageUploadUrl: '${ctx}/blog/ckeditorUpload'
	};

	CKEDITOR.replace( 'editor1', config );
	
	var content = $("#entity\\.content").val();
    if (content != ''){
        CKEDITOR.instances.editor1.setData(content);
    }
	
	$("#input-image-1").fileinput({
	    allowedFileExtensions: ["jpg", "png", "gif"],
	    maxImageWidth: 200,
	    maxFileCount: 1,
        showUpload: false,
	    resizeImage: true,
	    language: 'zh'
	});
	
	function gosave(){
		var data = CKEDITOR.instances.editor1.getData();
		$("#entity\\.content").val(data);
		var result = $("#form1").validationEngine('validate');
		if(result){
			var summary = $("#entity\\.summary").val();
			if(summary == undefined || $.trim(summary) == ""){
				$("#entity\\.summary").val(CKEDITOR.instances.editor1.document.getBody().getText());
			}
			$("#form1").attr("action","${ctx}/blog/saveOrUpdate");
			$("#form1").submit();
		}
	}
	
	function goback(){
		window.location.href = "${ctx}";
	}
	
	// 弹出分类管理页面
	function alertTypeManage(){
		layer.open({
		    type: 2,
		    title: '多级字典管理',
		    fix: false,
		    maxmin: true,
		    shadeClose: true,
		    area: ['800px', '400px'],
		    content: '${ctx}/busi/sys/treeTypeManage-index.action'
		  });
	}
	
	// 
	function customTypeClick(obj){
		$("#entity\\.customType").val("");
		loadCustomType(obj);
	}
	
	function loadCustomType(obj){
		$("body").mLoading({text:'加载自定义分类...'});
		$.ajax({
			url: '${ctx}/blog/manage/blogManage-queryCustomType',
			data:{code: obj.value},
			method:'POST',
			dataType: "json",
			success:function(data){
				$("body").mLoading('hide');
				var radioCustomType = "&nbsp;&nbsp;&nbsp;";
				if(data != undefined && data != ''){
					var requestResult = eval(data);
					for(var i = 0; i < requestResult.length; i++){
						radioCustomType += '<input onclick="selectedCustomType(this,\''+requestResult[i].name+'\')" type="checkbox" name="customType" value="'+requestResult[i].name+'" /> ' + requestResult[i].name;
						radioCustomType += "&nbsp;&nbsp;&nbsp;";
					}
				}
				$("#divCustomType").html(radioCustomType);
				reverseSelectCustomType();
			},
			error:function(jqXHR, textStatus, errorThrown){
				$("body").mLoading('hide');
				Zf.alert("请求失败，请联系管理员！", 5);
			}
		});
	}
	
	function selectedCustomType(obj, typeName){
		var customType = $("#entity\\.customType").val();
		if(obj.checked == false){
			var customTypes = customType.split(",");
			var newCustomtypes = new Array();
			var count = 0;
			for(var i = 0; i < customTypes.length; i++){
				if(typeName != customTypes[i]){
					newCustomtypes[count++] = customTypes[i];
				}
			}
			$("#entity\\.customType").val(newCustomtypes.join(','));
		}else{
			if($.trim(customType) != ''){
				typeName = "," + typeName;
			}
			$("#entity\\.customType").val(customType + typeName);
		}
	}
	
	// 根据文本框输入的内容，反向勾选自定义分类
	function reverseSelectCustomType(){
		var customType = $("#entity\\.customType").val();
		customType = $.trim(customType);
		if(customType != ''){
			var customTypes = customType.split(",");
			for(var i = 0; i < customTypes.length; i++){
				$("input[name='customType']").each(function(){
					if(this.value == customTypes[i]){
						this.checked = true;
					}
				});
			}
			$("input[name='customType']").each(function(){
				var exists = false;
				for(var j = 0; j < customTypes.length; j++){
					if(this.value == customTypes[j]){
						exists = true;
					}
				}
				if(!exists){
					this.checked = false;
				}
			});
		}else{
			$("input[name='customType']:checked").each(function(){
				this.checked = false;
			});
		}
	}
	
</script>
</html>