<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="<%=request.getContextPath()%>" scope="session"/>

<!--[if lt IE 9]> 
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<link rel="stylesheet" href="${ctx}/framework/bootstrap-3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/framework/select2/css/select2.min.css">
<link rel="stylesheet" href="${ctx}/framework/buttons/css/buttons.css">
<link rel="stylesheet" href="${ctx}/framework/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/framework/jQuery-Validation-Engine/css/validationEngine.jquery.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/framework/jquery/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${ctx}/framework/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/framework/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/framework/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/framework/jQuery-Validation-Engine/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/framework/jQuery-Validation-Engine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx }/framework/myModal/myModal.js"></script>
<script src="${ctx }/common/common.js"></script>
<script type="text/javascript">
	var ctx = '${ctx}';
</script>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
	.form-table .active{
		width: 10%;
		text-align: center;
		vertical-align: middle;
	}
	
	.form-table input[type='text'],
	.form-table select{
		width: 200px;
	}
	
	.form-table label{
		padding-left: 0px;
		padding-right: 0px;
		width: 100%;
	}
</style>