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
<link rel="stylesheet" href="${ctx }/framework/bootstrap-spinner/bootstrap-spinner.css">
</head>
<body>
	<div id="content" style="width: 90%;margin-top: 10px;">
		<form action="" id="form1" class="form-horizontal" role="form" autocomplete="false">
		  <s:hidden id="entity.parentId" name="entity.parentId"></s:hidden>
		  <s:hidden id="entity.id" name="entity.id"></s:hidden>
		  <div class="form-group">
		    <label for="code" class="col-sm-2 control-label">分类编号</label>
		    <div class="col-sm-10">
		      <s:textfield readonly="true" cssClass="form-control" id="entity.code" name="entity.code" placeholder="系统自动生成"></s:textfield>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="name" class="col-sm-2 control-label">分类名称</label>
		    <div class="col-sm-10">
		      <s:textfield id="entity.name" name="entity.name" cssClass="form-control validate[required,maxSize[100]]" ></s:textfield>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="name" class="col-sm-2 control-label">排序号</label>
		    <div class="input-group spinner col-sm-10" style="padding-left: 15px; padding-right: 15px;" data-trigger="spinner">
		      <input type="text" class="form-control text-center" id="entity.orderNo" 
		        name="entity.orderNo" value="${entity.orderNo }" data-rule="quantity" >
	          <div class="input-group-addon">
	            <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-caret-up"></i></a>
	            <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-caret-down"></i></a>
	          </div>
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
<script type="text/javascript" src="${ctx }/framework/bootstrap-spinner/jquery.spinner.min.js"></script>
<script type="text/javascript">
	$("#saveBtn").click(function(){
		var result = $("#form1").validationEngine('validate');
		if(result){
			Common.save("${ctx}/busi/sys/treeTypeManage-save.action", "form1", goback);
		}
	});
	
	$("#backBtn").click(function(){
		goback();
	});
	
	function goback(){
		window.location.href = "${ctx }/busi/sys/treeTypeManage-index.action";
	}
</script>
</html>