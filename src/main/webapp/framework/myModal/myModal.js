/**
 模态框 zf 2016年12月7日
*/
function MyModal(){
	this.title = '标题';
	this.msg = '提示消息';
	
	this.alert = function(msg,fun){
		var existsModal = $("#my-modal-01");
		if(existsModal != undefined || existsModal != null){
			$("#my-modal-01").remove();
		}
		
		var modalStr = '<div class="modal fade" id="my-modal-01" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> <div class="modal-dialog">   <div class="modal-content">     <div class="modal-header">       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>       <h4 class="modal-title" id="myModalLabel">提示</h4>     </div>     <div class="modal-body">'+
			msg+'</div>     <div class="modal-footer">       <a href="#" class="button button-primary button-rounded button-small" onclick="myModal.close('+
			fun+')">确定</a></div>   </div> </div></div>';
		$("body").append(modalStr);
		$("#my-modal-01").modal('toggle');
	}
	
	this.close = function(fun){
		$("#my-modal-01").modal('hide');
		if(fun && (typeof fun == "function")){
			fun();
		}
	}
	
	this.iframe = function(title,url,width,height){
		$(".modal-dialog").css("width","200px");
	}
}

var myModal = new MyModal();