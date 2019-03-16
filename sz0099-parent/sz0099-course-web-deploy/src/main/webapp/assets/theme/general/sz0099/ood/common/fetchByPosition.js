function checkShowFlag(inputId, wrapperId){
	var dataInput=$("#"+inputId);
	var divWrapper=$("#"+wrapperId);
	if(dataInput.length>0){
		var wrapper=divWrapper.find("div").length;
		if(wrapper<1){
			dataInput.val(0);
		}
		var value=dataInput.val();
		if(value==1){
			//已加载，直接显示
			return true;
		}else{
			//未加载，调用异步加载
			return false;
			//fecthByPosition(inputId,wrapperId);
		}
	}
	return true;
}

function fecthByPosition(inputId, wrapperId){
	var dataInput=$("#"+inputId);
	var divWrapper=$("#"+wrapperId);
	//var checked = checkShowFlag(inputId, wrapperId);
	var value=dataInput.val();
	if(value==1){
		return true;
	}
	var url=dataInput.attr("data-url");
	var mainId=dataInput.attr("data-mainId");
	var subId=dataInput.attr("data-subId");
	var ponPanel=dataInput.attr("data-ponPanel");
	
	var uploadData={
	    	'mainId' : mainId,
	    	'subId' : subId,
	    	'ponPanel' : ponPanel
    }
	
	axios({
	    method: 'post',
	    url: url,
	    data: 
	    	Qs.stringify(uploadData)
	    
	})
	.then(function (response) {
		divWrapper.html(response.data);
		//获取成功标志
		var success = $("#id_hidden_common_success").val();
		if(success==1){
			$("#id_hidden_common_success").remove();
 	    	$("#id_common_respMsg").remove();
 	    	$("#id_hidden_common_respCode").remove();
 	    	// 显示，成功时填充
 	    	dataInput.val(1);
	    	showFlag(inputId, wrapperId);
		}
	})
	.catch(function (error) {
	    console.log(error);
	});
}

function showFlag(inputId, wrapperId){
	var dataInput=$("#"+inputId);
	var divWrapper=$("#"+wrapperId);
	var checked = checkShowFlag(inputId, wrapperId);
	var title=dataInput.attr("data-title");
	var btnConfirm=dataInput.attr("data-btnConfirm");
	var btnCssClass=dataInput.attr("data-btnCssClass");
	var type=dataInput.attr("data-btnType");
	var typeV=BootstrapDialog.TYPE_DANGER;
	var confirmUrl=dataInput.attr("data-urlConfirm");
	if(type){
		typeV=eval(type);
	}
	if(!btnCssClass){
		btnCssClass="btn-primary";
	}
	if(!checked){
		fecthByPosition(inputId, wrapperId);
	}else{
		BootstrapDialog.show({
			title: title,
	    	message: $('<div></div>').html(divWrapper.html()),
	    	size: BootstrapDialog.SIZE_SMALL,
	    	type : typeV,
	    	draggable: true,
	    	buttons: [{
	            label: btnConfirm,
	            cssClass : btnCssClass,
	            action: function(dialog){
	            	if(confirmUrl){
	            		window.location.href=confirmUrl;
	            	}
	            	dialog.close();
	            },
	        }, {
	            label: '关闭',
	            cssClass : "btn-danger",
	            action: function(dialog){
	            	BootstrapDialog.closeAll();
	            }
	        }]
		});
	}
}
