<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多级字典管理</title>
<jsp:include page="/common/header.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx }/framework/jquery-treegrid/css/jquery.treegrid.css">
<link rel="stylesheet" href="${ctx }/common/css/loading.css">
</head>
<body>
	<div>
		<nav class="navbar navbar-default" role="navigation" style="margin-bottom:0px;">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		      </button>
		      <span class="navbar-brand" >多级字典管理列表</span>
		    </div>
		
			<form class="navbar-form navbar-left" role="search">
			  <div class="form-group">
			    <font>字典分类：</font>
			    <s:select list="#request.dictionaryTypes" id="entity.typeCode" name="entity.typeCode" class="form-control"></s:select>
			  </div>
			</form>

		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <a href="#" class="button button-primary button-rounded button-small nav navbar-right" 
		      	style="margin-top: 10px;padding-left:10px;padding-right: 10px;" onclick="add()"><i class="fa fa-plus"></i> 增加</a>
		    </div>
		  </div>
		</nav>
        <table id="treeDemo">
        </table>
   </div>
	
</body>
<script src="${ctx }/framework/jquery-treegrid/js/jquery.treegrid.js"></script>
<script src="${ctx }/framework/jquery-treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script src="${ctx }/framework/jquery-treegrid/js/jquery.treegrid.extend.zf.js"></script>
<script type="text/javascript">
	$(function(){
		var columns = [{"name":"id","text":"id","isHidden":true},
		               {"name":"isLeaf","text":"isLeaf","isHidden":true,formatter: myFormatter},
	               	   {"name":"name","text":"名称","header-align":"left","width":"50%"}];
		var operates = [{"text":"增加","icon":"fa fa-plus","method":"goadd"},
		                {"text":"修改","icon":"fa fa-pencil","method":"goedit","params":"id"},
		                {"text":"删除","icon":"fa fa-trash-o","method":"godelete","params":"id"}];
		var config = {
				url:ctx+'/busi/sys/treeTypeManage-indexTreeJson.action',
				columns:columns,
				operates:operates};
		$("#treeDemo").zfTreeGrid(config);
		
		$("#entity\\.typeCode").select2();
	});
	
	function myFormatter(value, row){
		if(value == '0'){
			return false;
		}else{
			return true;
		}
	}
	
	function add(){
		var nodeId = $("#treeDemo").treegrid("getSelectedNodeId");
		if($("#treeDemo").treegrid("getAllNodes").length > 0 && nodeId == ""){
			myModal.alert("请选择节点！");
			return;
		}
		window.location.href = "${ctx }/busi/sys/treeTypeManage-add.action?parentId="+nodeId;
	}
</script>
</html>