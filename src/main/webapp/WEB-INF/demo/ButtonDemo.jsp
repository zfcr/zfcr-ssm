<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单示例</title>
<jsp:include page="/common/header.jsp"></jsp:include>
</head> 
<body>
<div class="panel panel-success" style="width: 800px;margin: 0 auto;">
    <div class="panel-heading">
        <h3 class="panel-title">按钮Demo</h3>
    </div>
    <div class="panel-body">
    	<a href="#" class="button button-primary button-rounded button-small" 
		      	style="margin-top: 10px;margin-left:5px;padding-left:10px;padding-right: 10px;" onclick="gosave()"><i class="fa fa-tag"></i> 修改</a>
    	<a href="#" class="button button-primary button-rounded button-small" 
		      	style="margin-top: 10px;padding-left:10px;padding-right: 10px;" onclick="add()"><i class="fa fa-plus"></i> 增加</a>
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
</body>
<script type="text/javascript">
$("#select-multiple-love").select2({multiple:true});
$("#select-single-address").select2();
$("#form1").validationEngine();
function gosave(){
	$("#form1").validationEngine('validate');
}
</script>
</html>