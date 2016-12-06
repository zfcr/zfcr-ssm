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
	
	<div>
		<textarea id="editor1" name="editor1" data-sample="1" data-sample-preservewhitespace="">
		</textarea>
	</div>
</body>
<script src="${ctx }/framework/ckeditor/vendor/ckeditor/ckeditor.js"></script>
<script src="${ctx }/framework/ckeditor/assets/picoModal-2.0.1.min.js"></script>
<script src="${ctx }/framework/ckeditor/assets/contentloaded.js"></script>
<script src="${ctx }/framework/ckeditor/assets/simplesample.js"></script>
<script src="${ctx }/framework/ckeditor/assets/beautify-html.js"></script>
<script src="${ctx }/framework/ckeditor/assets/sample.js"></script>
<!--[if lt IE 9]>
	<script src="${ctx }/framework/ckeditor/assets/html5shiv.min.js"></script>
<![endif]-->
<script type="text/javascript">
	var config = {
		extraPlugins: 'codesnippet',
		codeSnippet_theme: 'monokai_sublime',
		height: 356
	};

	CKEDITOR.replace( 'editor1', config );
</script>
</html>