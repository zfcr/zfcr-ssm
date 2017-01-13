<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单示例</title>
<jsp:include page="/common/header.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/framework/zTree-v3/css/metroStyle/metroStyle.css">
</head> 
<body>
<div class="panel panel-success" style="width: 800px;margin: 0 auto;">
    <div class="panel-heading">
        <h3 class="panel-title">表单示例-表格布局-控件Demo</h3>
    </div>
    <div class="panel-body">
    	<form action="" id="form1" name="form1">
	        <table class="table table-bordered form-table">
				<tr>
					<td class="active">
						<label class="col-sm-2 control-label" for="name">姓名</label>
					</td>
					<td>
						<input type="text" class="form-control input-sm validate[required]" id="name">
					</td>
					<td class="active">
						<label class="col-sm-2 control-label" for="age">年龄</label>
					</td>
					<td>
						<input type="text" class="form-control input-sm" id="age">
					</td>
				</tr>
				<tr>
					<td class="active">
						<label class="col-sm-2 control-label">性别</label>
					</td>
					<td>
						 <input type="radio" name="sex" id="sex0" value="0" checked>男
						 <input type="radio" name="sex" id="sex1" value="1" checked>女
					</td>
					<td class="active">
						<label class="col-sm-2 control-label" for="address">所在地区</label>
					</td>
					<td>
						<select id="select-single-address" class="form-control">
						  <option value=""></option>	
						  <option value="GD">广东</option>
						  <option value="BJ">北京</option>
						  <option value="HN">湖南</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="active">
						<label class="col-sm-2 control-label" for="select-multiple-love">爱好</label>
					</td>
					<td>
						<select id="select-multiple-love" class="form-control">
						  <option value="AL">Alabama</option>
						  <option value="AL">Alabab</option>
						  <option value="WY">Wyoming</option>
						</select>
					</td>
					<td class="active">
						<label class="col-sm-2 control-label" for="birthday">出生日期</label>
					</td>
					<td>
						<input type="text" class="form-control input-sm" id="birthday" onclick="WdatePicker()">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						  <a href="#" class="button button-primary button-rounded button-small" onclick="gosave()">保存</a>
						  &nbsp;&nbsp;&nbsp;
						  <a href="#" class="button button-rounded button-small">重置</a>
					</td>
				</tr>
			</table>
		</form>
    </div>
</div>
<div style="height: 5px;"></div>
<div class="panel panel-success" style="width: 800px;margin: 0 auto;">
    <div class="panel-heading">
        <h3 class="panel-title">表单示例-DIV布局-水平排列</h3>
    </div>
    <div class="panel-body">
		<form class="form-horizontal" role="form">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="type1" class="col-sm-2 control-label">分类</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="type1" placeholder="点击选择分类" readonly="readonly" onclick="selectType()">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> Remember me
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">Sign in</button>
		    </div>
		  </div>
		</form>
	</div>
</div>

<div id="menuContent" class="menuContent" style="display:none; position: absolute;border: 1px solid gray;background-color: white;overflow: auto;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>
</body>
<script type="text/javascript" src="${ctx}/framework/zTree-v3/js/jquery.ztree.core.js"></script>
<script type="text/javascript">
$("#select-multiple-love").select2({multiple:true});
$("#select-single-address").select2();
$("#form1").validationEngine();
function gosave(){
	$("#form1").validationEngine('validate');
}

function selectType(){
	var setting = {
            view: {
                selectedMulti: true
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            edit: {
                enable: true
            }
        };

        var zNodes =[
            { id:1, pId:0, name:"父节点1", open:true},
            { id:11, pId:1, name:"父节点11"},
            { id:111, pId:11, name:"叶子节点111"},
            { id:112, pId:11, name:"叶子节点112"},
            { id:113, pId:11, name:"叶子节点113"},
            { id:114, pId:11, name:"叶子节点114"},
            { id:12, pId:1, name:"父节点12"},
            { id:121, pId:12, name:"叶子节点121"},
            { id:122, pId:12, name:"叶子节点122"},
            { id:123, pId:12, name:"叶子节点123"},
            { id:124, pId:12, name:"叶子节点124"},
            { id:13, pId:1, name:"父节点13", isParent:true},
            { id:2, pId:0, name:"父节点2"},
            { id:21, pId:2, name:"父节点21", open:true},
            { id:211, pId:21, name:"叶子节点211"},
            { id:212, pId:21, name:"叶子节点212"},
            { id:213, pId:21, name:"叶子节点213"},
            { id:214, pId:21, name:"叶子节点214"},
            { id:22, pId:2, name:"父节点22"},
            { id:221, pId:22, name:"叶子节点221"},
            { id:222, pId:22, name:"叶子节点222"},
            { id:223, pId:22, name:"叶子节点223"},
            { id:224, pId:22, name:"叶子节点224"},
            { id:23, pId:2, name:"父节点23"},
            { id:231, pId:23, name:"叶子节点231"},
            { id:232, pId:23, name:"叶子节点232"},
            { id:233, pId:23, name:"叶子节点233"},
            { id:234, pId:23, name:"叶子节点234"},
            { id:3, pId:0, name:"父节点3", isParent:true}
        ];

	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	var thisOffSet = $("#type1").offset();
	var width=200,height=300;
	$("#menuContent").css({left:thisOffSet.left + "px", top:thisOffSet.top + $("#type1").outerHeight() + "px", width:width+"px", height:height+"px"}).slideDown("fast");
	
	$("body").bind("mousedown", hideSelectMenu);
	$(window).resize(function(){
		var thisOffSet = $("#type1").offset();
		$("#menuContent").css({left:thisOffSet.left + "px", top:thisOffSet.top + $("#type1").outerHeight() + "px"}).slideDown("fast");
	});
}

function hideSelectMenu(){
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", hideSelectMenu);
	}
}
</script>
</html>