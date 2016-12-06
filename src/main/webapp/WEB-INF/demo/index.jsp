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