/**
 模态框 zf 2016年12月7日
*/
function MyModal(){
	this.modalType = "alert";
	this.title = "Modal Title";
	this.ok = "OK";
	this.cancel = "Cancel";
}

MyModal.prototype.alert = function(config){
	var existsModal = $("#my-modal-01");
	if(existsModal != undefined || existsModal != null){
		$("#my-modal-01").remove();
	}
	var isJson = typeof(config) == "object" && Object.prototype.toString.call(config).toLowerCase() == "[object object]" && !config.length;
	if(!isJson){
		config = {title:'标题',msg:'提示消息',yesTip:'成功'};
	}
	var modalStr = '<div class="modal fade" id="my-modal-01" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> <div class="modal-dialog">   <div class="modal-content">     <div class="modal-header">       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>       <h4 class="modal-title" id="myModalLabel">'+
		config.title + '</h4>     </div>     <div class="modal-body">'+
		config.msg+'</div>     <div class="modal-footer">       <a href="#" class="button button-primary button-rounded button-small" onclick="gosave()">确定</a></div>   </div> </div></div>';
	$("body").append(modalStr);
	$("#my-modal-01").modal('toggle');
}