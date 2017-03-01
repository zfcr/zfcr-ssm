/**
 * 公共方法
 */

// ajax异步调用封装
function ajaxSyncCall(url, formId, sucFun){
	$.ajax({
		url: url,
		data:$('#'+formId).serialize(),
		method:'POST',
		dataType: "text",
		success:function(data){
			var requestResult = eval("("+data+")");
			if(requestResult.success){
				if(sucFun != null && sucFun != undefined && $.type(sucFun) === "function"){
					sucFun(requestResult);
				}else{
					Zf.alert(requestResult.msg, 6);
				}
			}else{
				Zf.alert(requestResult.msg, 5);
			}
		},
		error:function(jqXHR, textStatus, errorThrown){
			console.log(textStatus);
			console.log(errorThrown);
			Zf.alert("请求失败，请联系管理员！", 5);
		}
	});
}

var Common = {
	deleted: function(url, fun){
		var timestamp=(new Date()).valueOf();
		if(url.indexOf("?")>=0){
			url += "&" + timestamp;
		}else{
			url += "?" + timestamp;
		}
		$.ajax({
			url: url,
			method:'GET',
			dataType: "text",
			success:function(data){
				var requestResult = eval("("+data+")");
				if(requestResult.success){
					if(fun != null && fun != undefined && $.type(fun) === "function"){
						Zf.alert("删除成功！", 6, fun);
						return;
					}
					Zf.alert("删除成功！", 6);
				}else{
					Zf.alert(requestResult.msg, 5);
				}
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
				console.log(errorThrown);
				Zf.alert("请求失败，请联系管理员！", 5);
			}
		});
	},
	save: function(url, formId, sucFun){
		$("body").mLoading({text:'保存中...'});
		$.ajax({
			url: url,
			data:$('#'+formId).serialize(),
			method:'POST',
			dataType: "text",
			success:function(data){
				$("body").mLoading('hide');
				var requestResult = eval("("+data+")");
				if(requestResult.success){
					Zf.alert("保存成功！", 6, sucFun);
				}else{
					Zf.alert(requestResult.msg, 5);
				}
			},
			error:function(jqXHR, textStatus, errorThrown){
				$("body").mLoading('hide');
				console.log(textStatus);
				console.log(errorThrown);
				Zf.alert("请求失败，请联系管理员！", 5);
			}
		});
	},
	// 异步Url访问
	asyncCallUrl: function(url){
		$.ajax({
			url: url,
			method:'GET',
			dataType: "text"
		});
	}
};

var Zf = {
	// 调用layer框架的弹出层插件，简化它的一些参数，方便调用。
	alert: function(msg, icon, fun){
		if(icon == undefined){
			icon = -1;
		}
		layer.alert(msg, {icon:icon,title:'提示'}, fun);
	}
};