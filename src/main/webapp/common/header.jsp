<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="<%=request.getContextPath()%>" scope="session"/>

<!--[if lt IE 9]> 
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<link rel="stylesheet" href="${ctx}/framework/bootstrap-3.3.5/css/bootstrap.css">
<link rel="stylesheet" href="${ctx}/framework/select2/css/select2.min.css">
<link rel="stylesheet" href="${ctx}/framework/buttons/css/buttons.css">
<link rel="stylesheet" href="${ctx}/framework/font-awesome-4.7.0/css/font-awesome.css">
<link rel="stylesheet" href="${ctx}/framework/jQuery-Validation-Engine/css/validationEngine.jquery.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/framework/loading-Mask/jquery.mloading.css">
<link rel="stylesheet" href="${ctx}/common/css/style.css">
<script type="text/javascript" src="${ctx}/framework/jquery/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${ctx}/framework/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/framework/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="${ctx}/framework/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/framework/jQuery-Validation-Engine/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/framework/jQuery-Validation-Engine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/framework/layer/layer.js"></script>
<script type="text/javascript" src="${ctx }/framework/loading-Mask/jquery.mloading.js"></script>
<script src="${ctx }/common/common.js"></script>
<script type="text/javascript">
	var ctx = '${ctx}';
</script>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
	a:link {color: black;}
    a:visited {color: black;}
    a:hover, a:active {color: red;}
    a:hover {text-decoration: underline;}
    
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
	
    .navbar {
        margin-bottom: 5px;
    }
    
    .footer {
        margin-top: 20px;
    }
    
    .apostrophe {
        overflow:hidden;
        text-overflow:ellipsis;
    }
    
    body,html {
    	font-family:"Microsoft Yahei","Helvetica Neue",Helvetica,Arial,sans-serif;
    	font-size: 15px;
    	background-color: #378F07;
    }
    
    .new-title a, .title-type a {
    	color: #404040;
    }
    
    .content a:hover, .content a:active, .rec a:hover, .rec a:active {color: red;}
    .content a:hover, .rec a:hover {text-decoration: underline;}
    
    .titleGrid .number{width:20px;height:20px;line-height:20px;text-align:center;border-radius:4px;color:#fff;background-color:#979598;}
	.titleGrid .one{background-color:rgb(255,0,0)}
	.titleGrid .two{background-color:rgb(255,128,0)}
	.titleGrid .three{background-color:rgb(255,255,0)}
	.titleGrid .four{background-color:rgb(0,255,0)}
	.titleGrid .five{background-color:rgb(0,255,255)}
	.titleGrid .six{background-color:rgb(0,0,255)}
	.titleGrid .seven{background-color:rgb(128,0,255)}
</style>