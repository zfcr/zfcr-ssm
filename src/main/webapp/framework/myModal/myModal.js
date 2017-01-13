/**
 模态框 zf 2016年12月7日
*/
function MyModal(){
	this.title = '标题';
	this.msg = '提示消息';
	this.closeAfterFun = function(){}
	
	this.alert = function(msg,fun){
		this.closeAfterFun = fun;
		var existsModal = $("#my-modal-01");
		if(existsModal != undefined || existsModal != null){
			$("#my-modal-01").remove();
		}
		
		var modalStr = '<div class="modal fade" id="my-modal-01" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> <div class="modal-dialog">   <div class="modal-content">     <div class="modal-header">       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>       <h4 class="modal-title" id="myModalLabel">提示</h4>     </div>     <div class="modal-body">'+
			msg+'</div>     <div class="modal-footer">       <a href="#" class="button button-primary button-rounded button-small" onclick="myModal.close()">确定</a></div>   </div> </div></div>';
		$("body").append(modalStr);
		$("#my-modal-01").modal('toggle');
	}
	
	this.close = function(){
		$("#my-modal-01").modal('hide');
		if(this.closeAfterFun && (typeof this.closeAfterFun == "function")){
			this.closeAfterFun();
		}
	}
	
	this.iframe = function(title,url,width,height){
		var iframeModalHtml = '<div class="modal fade" id="zf-modal-iframe-01" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'
			+'  <div class="modal-dialog">'
			+'    <div class="modal-content">'
			+'      <div class="modal-header">'
			+'        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+'        <h4 class="modal-title" id="myModalLabel">'+title+'</h4>'
			+'      </div>'
			+'      <div class="modal-body" style="padding:2px;">'
			+'        <iframe src="'+url+'" scrolling="no" style="height: 100%;width: 100%;" frameborder="0"></iframe>'
			+'      </div>'
			+'    </div>'
			+'</div>';
		$("body").append(iframeModalHtml);
		$("#zf-modal-iframe-01").modal();
	}
}

var myModal = new MyModal();