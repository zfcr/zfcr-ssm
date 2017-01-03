<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表格-树形表格示例</title>
<jsp:include page="/common/header.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx }/framework/jquery-treegrid/css/jquery.treegrid.css">
<link rel="stylesheet" href="${ctx }/common/css/loading.css">
</head>
<body>
	<table id="treeDemo"></table>
</body>
<script src="${ctx }/framework/jquery-treegrid/js/jquery.treegrid.js"></script>
<script src="${ctx }/framework/jquery-treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script src="${ctx }/framework/jquery-treegrid/js/jquery.treegrid.extend.zf.js"></script>
<script type="text/javascript">
	$(function(){
		var columns = [{"name":"id","text":"id","isHidden":true},
	               {"name":"name","text":"名称","header-align":"left","width":"50%"},
					{"name":"age","text":"年龄","width":"30%"}];
		var operates = [{"text":"增加","icon":"fa fa-plus","method":"goadd"},
		                {"text":"修改","icon":"fa fa-pencil","method":"goedit","params":"id"},
		                {"text":"删除","icon":"fa fa-trash-o","method":"godelete","params":"id"}];
		var config = {
				url:ctx+'/demo-indexTreeGridDnJson.action',
				columns:columns,
				operates:operates,
				width:'90%'};
		$("#treeDemo").zfTreeGrid(config);
	});
	
</script>
</html>