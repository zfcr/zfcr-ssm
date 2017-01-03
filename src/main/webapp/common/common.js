/**
 * 公共方法
 */

// ajax异步调用封装
function ajaxSyncCall(url, formId, sucUrl, fun){
	$.ajax({
		url: url,
		data:$('#'+formId).serialize(),
		method:'POST',
		dataType: "text",
		success:function(data){
			var requestResult = eval("("+data+")");
			if(requestResult.success){
				if(fun != null && fun != undefined && $.type(fun) === "function"){
					fun(requestResult);
				}else{
					if(sucUrl != undefined && $.type(sucUrl) === "string"){
						myModal.alert(requestResult.msg, function(){window.location.href = sucUrl;});
					}else{
						myModal.alert(requestResult.msg);
					}
				}
			}else{
				myModal.alert(requestResult.msg);
			}
		},
		error:function(jqXHR, textStatus, errorThrown){
			console.log(textStatus);
			console.log(errorThrown);
			alert("请求失败，请联系管理员！");
		}
	});
}