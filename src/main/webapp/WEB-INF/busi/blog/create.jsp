<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建新博客</title>
<jsp:include page="/common/header.jsp"></jsp:include>
</head>
<body>
	
	<div>
		<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
	</div>
</body>
<script type="text/javascript" charset="utf-8" src="${ctx }/framework/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/framework/ueditor1_4_3_3/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctx }/framework/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	var ue = UE.getEditor('editor');
</script>
</html>