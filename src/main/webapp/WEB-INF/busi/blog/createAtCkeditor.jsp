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
	
	<div align="center" style="margin: 0px auto;min-width: 1200px;width: 80%;">
		<textarea id="editor1" name="editor1" data-sample="1" data-sample-preservewhitespace="">
		</textarea>
		<div style="height: 5px;"></div>
		<div>
			<a href="#" class="button button-primary button-rounded button-small" onclick="gosave()">保存</a>
		</div>
	</div>
</body>
<script src="${ctx }/framework/ckeditor/vendor/ckeditor/ckeditor.js"></script>
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
</script>
</html>