<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建新博客-使用CKEDITOR框架</title>
<jsp:include page="/common/header.jsp"></jsp:include>
</head>
<body>
	<div style="margin: 0px auto;min-width: 1200px;width: 80%;">
		<form role="form">
		  <div class="form-group">
		    <label for="inputEmail3">文章标题</label>
		    <input type="email" class="form-control" id="inputEmail3" placeholder="请输入文章标题">
		  </div>
		  <div class="form-group">
		  	<label for="inputEmail3">文章内容(IE浏览器下，支持粘贴图片)</label>
		    <div>
		     	<textarea id="editor1" name="editor1" data-sample="1" data-sample-preservewhitespace="">
				</textarea>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail3">
		    	文章分类
		    	<a href="#" class="button button-primary button-rounded button-small" onclick="alertTypeManage()">分类管理</a>
		    </label>
		    <input type="email" class="form-control" id="inputEmail3" placeholder="请输入文章标题">
		  </div>
		</form>
		
	
		
		<div style="height: 5px;"></div>
		<div>
			<a href="#" class="button button-primary button-rounded button-small" onclick="gosave()">提交</a>
			<a href="#" class="button button-rounded button-small" onclick="gosave()">取消</a>
		</div>
	</div>
	
</body>
<script src="${ctx }/framework/ckeditor/vendor/ckeditor/ckeditor.js"></script>
<script src="${ctx }/framework/myModal/myModal.js"></script>
<script type="text/javascript">
	var config = {
		extraPlugins: 'codesnippet,colorbutton,colordialog',
		codeSnippet_theme: 'monokai_sublime',
		height: 356
	};

	CKEDITOR.replace( 'editor1', config );
	
	function gosave(){
		var data = CKEDITOR.instances.editor1.getData();
		alert(data);
	}
	
	// 弹出分类管理页面
	function alertTypeManage(){
// 		var modalStr = '<div class="modal fade" id="bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> <div class="modal-dialog">   <div class="modal-content">     <div class="modal-header">       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>       <h4 class="modal-title" id="myModalLabel">Modal title</h4>     </div>     <div class="modal-body">       	内容     </div>     <div class="modal-footer">       <a href="#" class="button button-primary button-rounded button-small" onclick="gosave()">确定</a>&nbsp;&nbsp;<a href="#" class="button button-rounded button-small" onclick="gosave()">取消</a>     </div>   </div> </div></div>';
// 		$("body").append(modalStr);
// 		$("#bs-example-modal-lg").modal('toggle');
		var config = {title:'提示',msg:'保存成功'};
		myModal.alert("保存成功",function(){alert('11');});
	}
</script>
</html>