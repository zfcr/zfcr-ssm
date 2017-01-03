<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加分类</title>
<jsp:include page="/common/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="${ctx }/common/css/button.css">
</head>
<body>
	<div id="content" style="width: 90%;margin-top: 10px;">
		<form action="" id="form1" class="form-horizontal" role="form">
		  <s:hidden id="entity.parentId" name="entity.parentId"></s:hidden>
		  <div class="form-group">
		    <label for="code" class="col-sm-2 control-label">分类编号</label>
		    <div class="col-sm-10">
		      <s:textfield readonly="true" cssClass="form-control" id="entity.code" name="entity.code" placeholder="系统自动生成"></s:textfield>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="name" class="col-sm-2 control-label">分类名称</label>
		    <div class="col-sm-10">
		      <s:textfield id="entity.name" name="entity.name" cssClass="form-control" ></s:textfield>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10" align="center">
		      <button id="saveBtn" type="button" class="btn-zf">保存</button>
		      <button id="backBtn" type="button" class="btn-zf">返回</button>
		    </div>
		  </div>
		</form>
   </div>
</body>
<script type="text/javascript">
	$("#saveBtn").click(function(){
		ajaxSyncCall("${ctx}/busi/sys/treeTypeManage-save.action", "form1");
	});
	
	$("#backBtn").click(function(){
		window.location.href = "${ctx }/busi/sys/treeTypeManage-index.action";
	});
</script>
</html>